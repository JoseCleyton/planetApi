package com.planet.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.impl.PlanetServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PlanetServiceTest")
public class PlanetServiceTest {

	@MockBean
	private PlanetRepository planetRepository;

	@Autowired
	private PlanetServiceImpl planetServiceImpl;

	@Test
	@DisplayName("Should Add Planet")
	public void shouldAddPlanet() throws PayloadNotConsistentException {
		Planet planetMock = Mockito.mock(Planet.class);
		Mockito.when(planetMock.getId()).thenReturn(null);
		Mockito.when(this.planetRepository.save(ArgumentMatchers.eq(planetMock))).thenReturn(planetMock);
		Planet planet = this.planetServiceImpl.add(planetMock);
		assertEquals(planet.getName(), planetMock.getName());
		verify(this.planetRepository, times(1)).save(planetMock);
	}

	@Test
	@DisplayName("Should Update Planet")
	public void shouldUpdatePlanet() {
		Planet planetMock = Mockito.mock(Planet.class);
		Mockito.when(planetMock.getId()).thenReturn("1");
		Mockito.when(this.planetRepository.findById("1")).thenReturn(Optional.of(planetMock));
		Mockito.when(this.planetRepository.save(ArgumentMatchers.eq(planetMock))).thenReturn(planetMock);
		Optional<Planet> planet = this.planetServiceImpl.update(planetMock);
		assertTrue(planet.isPresent());
		verify(this.planetRepository, Mockito.times(1)).save(ArgumentMatchers.any(Planet.class));
	}

	@Test
	@DisplayName("Not Should Update Planet")
	public void notShouldUpdatePlanet() {
		Planet planetMock = Mockito.mock(Planet.class);
		Mockito.when(planetMock.getId()).thenReturn(null);
		Mockito.when(this.planetRepository.findById("1")).thenReturn(Optional.empty());
		Mockito.when(this.planetRepository.save(ArgumentMatchers.eq(planetMock))).thenReturn(planetMock);
		Optional<Planet> planet = this.planetServiceImpl.update(planetMock);
		assertTrue(planet.isEmpty());
	}

	@Test
	@DisplayName("Should List Planets")
	public void shouldListPlanets() {
		Planet planetMock1 = Mockito.mock(Planet.class);
		Planet planetMock2 = Mockito.mock(Planet.class);
		Planet planetMock3 = Mockito.mock(Planet.class);
		List<Planet> planets = new ArrayList<>();
		planets.add(planetMock1);
		planets.add(planetMock2);
		planets.add(planetMock3);
		Mockito.when(this.planetRepository.findAll()).thenReturn(planets);
		List<Planet> response = this.planetServiceImpl.findAll();
		assertFalse(response.isEmpty());
		verify(this.planetRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Should Find Planet By Id")
	public void shouldFindPlanetById() {
		Planet planetMock = Mockito.mock(Planet.class);
		Mockito.when(planetMock.getId()).thenReturn("1");
		Mockito.when(this.planetRepository.findById("1")).thenReturn(Optional.of(planetMock));
		Optional<Planet> planet = this.planetServiceImpl.findById("1");
		assertTrue(planet.isPresent());
		verify(this.planetRepository, Mockito.times(1)).findById("1");
	}

}
