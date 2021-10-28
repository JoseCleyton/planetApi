package com.planet.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.planet.api.document.Planet;
import com.planet.api.document.ResultApiSW;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	@Override
	public Planet add(Planet planet) {
		this.findPlanetByName(planet.getName()).getResults()
				.forEach(planetSw -> planet.setNumberOfApparitions(planetSw.getFilms().size()));
		;
		return this.planetRepository.save(planet);
	}

	@Override
	public Optional<Planet> update(Planet planet) {
		return this.findById(planet.getId()).map(record -> Optional.of(this.planetRepository.save(planet)))
				.orElse(Optional.empty());
	}

	@Override
	public Optional<Planet> findById(String id) {
		return this.planetRepository.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
	}

	@Override
	public List<Planet> findAll() {
		return this.planetRepository.findAll();
	}

	@Override
	public void delete(String id) {
		this.planetRepository.deleteById(id);

	}

	private ResultApiSW findPlanetByName(String name) {
		String fooResourceUrl = "https://swapi.dev/api/planets/?search=" + name;
		ResponseEntity<ResultApiSW> response = new RestTemplate().exchange(fooResourceUrl, HttpMethod.GET, null,
				ResultApiSW.class);
		return response.getBody();
	}

}
