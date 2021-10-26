package com.planet.api.controller;

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
import com.planet.api.controller.PlanetController;
import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourceAlreadyExistsException;
import com.planet.api.exceptions.ResourcesNotFoundException;
import com.planet.api.service.PlanetService;

import io.restassured.http.ContentType;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;;

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

		given().accept(ContentType.JSON).when().get("/planet").then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnSuccess_WhenFindByIdPlanet() throws ResourcesNotFoundException {
		Planet p1 = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		when(this.planetService.findById("1")).thenReturn(Optional.of(p1));

		given().accept(ContentType.JSON).when().get("/planet/{id}", "1").then().status(HttpStatus.OK);
	}

	@Test
	public void shouldReturnNotFound_WhenFindByIdPlanet() throws ResourcesNotFoundException {
		when(this.planetService.findById("2")).thenReturn(Optional.empty());

		given().accept(ContentType.JSON).when().get("/planet/{id}", "2").then().status(HttpStatus.NOT_FOUND);
	}

	@Test
	public void shouldReturnSuccess_WhenAddPlanet()
			throws ResourcesNotFoundException, JsonProcessingException, PayloadNotConsistentException {
		Planet planetMock = new Planet();
		planetMock.setName("Terra");
		planetMock.setClimate("Arenoso");
		planetMock.setGround("Árido");
		planetMock.setNumberOfApparitions(10);

		when(this.planetService.add(planetMock)).thenReturn(planetMock);

		given().body(objectMapper.writeValueAsString(planetMock)).contentType(ContentType.JSON).when().post("/planet")
				.then().status(HttpStatus.CREATED);

	}

	@Test
	public void shouldReturnSuccess_WhenUpdatePlanet()
			throws JsonProcessingException, PayloadNotConsistentException, ResourcesNotFoundException {
		Planet planetMock = new Planet("1", "Terra", "Arenoso", "Árido", 10);
		planetMock.setName("Jupiter");
		planetMock.setGround("Rochoso");
		planetMock.setClimate("Seco");

		when(this.planetService.findById("1")).thenReturn(Optional.of(planetMock));

		when(this.planetService.update(planetMock)).thenReturn(planetMock);

		given().body(objectMapper.writeValueAsString(planetMock)).contentType(ContentType.JSON).when().put("/planet")
				.then().status(HttpStatus.ACCEPTED);
	}

	@Test
	public void shouldReturnNotFound_WhenUpdatePlanetWithoutId()
			throws ResourcesNotFoundException, JsonProcessingException, PayloadNotConsistentException {
		Planet planetMock = new Planet();
		planetMock.setName("Terra");
		planetMock.setGround("Arenoso");
		planetMock.setClimate("Árido");
		planetMock.setNumberOfApparitions(10);

		when(this.planetService.findById("")).thenReturn(Optional.empty());

		when(this.planetService.update(planetMock)).thenReturn(null);

		given().body(objectMapper.writeValueAsString(planetMock)).contentType(ContentType.JSON).when().put("/planet")
				.then().status(HttpStatus.NOT_FOUND);

	}

//	@Test
//	public void shouldReturnSuccess_WhenDeletePlanetById() throws ResourcesNotFoundException, JsonProcessingException {
//		Planet planetMock = new Planet("1", "Terra", "Arenoso", "Árido", 10);
//		when(this.planetService.findById("1")).thenReturn(Optional.of(planetMock));
//
//		given().accept(ContentType.JSON).when().delete("/planet/{id}", "1").then().status(HttpStatus.OK);
//
//	}

//	@Test
//	public void shouldReturnNotFound_WhenDeletePlanetWithoutId()
//			throws ResourcesNotFoundException, JsonProcessingException {
//		when(this.planetService.findById("")).thenReturn(Optional.empty());
//
//		given().accept(ContentType.JSON).when().delete("/planet/{id}", "1").then().status(HttpStatus.NOT_FOUND);
//
//	}

}
