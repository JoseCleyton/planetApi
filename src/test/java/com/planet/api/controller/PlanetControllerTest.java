package com.planet.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.planet.api.document.Planet;
import com.planet.api.service.PlanetService;

import io.restassured.http.ContentType;;

@WebMvcTest
public class PlanetControllerTest {

	@Autowired
	private PlanetController planetController;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PlanetService planetService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.planetController);
	}

	@Test
	public void shouldReturnSuccess_WhenFetchAllPlanets() {
		Planet p1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		Planet p2 = new Planet("2", "Saturno", "Arenoso", "Árido", 10);
		Planet p3 = new Planet("3", "Urano", "Arenoso", "Árido", 10);
		List<Planet> planets = new ArrayList<>();
		planets.add(p1);
		planets.add(p2);
		planets.add(p3);
		when(this.planetService.findAll()).thenReturn(planets);

		given().accept(ContentType.JSON).when().get("/api/planet").then().status(HttpStatus.OK);
	}

	@Test
	public void shouldReturnSuccess_WhenFindByIdPlanet() {
		Planet p1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		when(this.planetService.findById("1")).thenReturn(Optional.of(p1));

		given().accept(ContentType.JSON).when().get("/api/planet/{id}", "1").then().status(HttpStatus.OK);
	}

	@Test
	public void shouldReturnSuccess_WhenFindByNamePlanet() {
		Planet p1 = new Planet("1", "Dagobah", "Arenoso", "Árido", 10);
		when(this.planetService.findByName("Dagobah")).thenReturn(Optional.of(p1));

		given().accept(ContentType.JSON).when().get("/api/planet/?{name}", "Dagobah").then().status(HttpStatus.OK);
	}

	@Test
	public void shouldReturnNotFound_WhenFindByIdPlanet() {
		when(this.planetService.findById("2")).thenReturn(Optional.empty());

		given().accept(ContentType.JSON).when().get("/api/planet/{id}", "2").then().status(HttpStatus.NOT_FOUND);
	}

	@Test
	public void shouldReturnNotFound_WhenUpdatePlanetBy() throws JsonProcessingException {
		Planet planetMock = new Planet("1", "Terra", "Arenoso", "Árido", 10);

		when(this.planetService.findById("1")).thenReturn(Optional.empty());
		when(this.planetService.update(planetMock)).thenReturn(Optional.empty());

		given().body(objectMapper.writeValueAsString(planetMock)).contentType(ContentType.JSON).when()
				.put("/api/planet").then().status(HttpStatus.NOT_FOUND);

	}

	@Test
	public void shouldReturnNotFound_WhenUpdatePlanetWithoutId() throws JsonProcessingException {
		Planet planetMock = new Planet("Terra", "Arenoso", "Árido", 10);

		when(this.planetService.findById("")).thenReturn(Optional.empty());

		when(this.planetService.update(planetMock)).thenReturn(null);

		given().body(objectMapper.writeValueAsString(planetMock)).contentType(ContentType.JSON).when()
				.put("/api/planet").then().status(HttpStatus.NOT_FOUND);

	}

	@Test
	public void shouldReturnSuccess_WhenDeletePlanetById() {
		Planet planetMock = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		when(this.planetService.findById("1")).thenReturn(Optional.of(planetMock));
		given().accept(ContentType.JSON).when().delete("/api/planet/{id}", "1").then().status(HttpStatus.OK);
	}

	@Test
	public void shouldReturnNotFound_WhenDeletePlanetById() {
		when(this.planetService.findById("1")).thenReturn(Optional.empty());
		given().accept(ContentType.JSON).when().delete("/api/planet/{id}", "1").then().status(HttpStatus.NOT_FOUND);
	}

}