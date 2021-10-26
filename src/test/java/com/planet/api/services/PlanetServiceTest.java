package com.planet.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourcesNotFoundException;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.impl.PlanetServiceImpl;

@ExtendWith(SpringExtension.class)
public class PlanetServiceTest {

	@TestConfiguration
	static class PlanetServiceTestConfiguration {
		@Bean
		public PlanetServiceImpl planetServiceImpl() {
			return new PlanetServiceImpl();
		}
	}

	@Autowired
	private PlanetServiceImpl planetServiceImpl;

	@MockBean
	private PlanetRepository planetRepository;

	@BeforeEach
	public void setup() {
		Planet planetMock1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		Planet planetMock2 = new Planet("2", "Saturno", "Arenoso", "Árido", 3);
		Planet planetMock3 = new Planet("3", "Jupiter", "Arenoso", "Árido", 8);

		List<Planet> planets = new ArrayList<>();
		planets.add(planetMock1);
		planets.add(planetMock2);
		planets.add(planetMock3);
		Mockito.when(planetRepository.findAll()).thenReturn(planets);
		Mockito.when(planetRepository.findById("1")).thenReturn(Optional.of(planetMock1));
//		Mockito.when(planetRepository.save(planetMock2)).thenReturn(planetMock2);
	}

	@Test
	public void shouldFindById() throws ResourcesNotFoundException {
		this.setupFindById();
		String id = "1";
		Planet planetMock = new Planet("2", "Saturno", "Arenoso", "Árido", 10);
		Planet planet = this.planetServiceImpl.findById(id).get();
		Assertions.assertEquals(planet, planetMock);
	}
	
	@Test
	public void shouldUpdate() throws ResourcesNotFoundException, PayloadNotConsistentException {
//		this.setupUpdate();
		Planet planetMock = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		Planet planet = this.planetServiceImpl.findById(planetMock.getId()).get();
		
//		Planet planetMockUpdate = new Planet("2", "Terra Update", "Rochoso", "Seco", 20);
		planet.setName("Terra Update");
		this.planetServiceImpl.update(planet);
		Assertions.assertEquals(planet.getName(), planetMock.getName());
	}
	
	public void setupFindById() {
		Planet planetMock1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		Mockito.when(planetRepository.findById(planetMock1.getId())).thenReturn(Optional.of(planetMock1));
	}

	public void setupUpdate() {
		Planet planetMock1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		Mockito.when(planetRepository.save(planetMock1)).thenReturn(planetMock1);
	}
}
