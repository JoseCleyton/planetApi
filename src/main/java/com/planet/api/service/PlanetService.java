package com.planet.api.service;

import java.util.List;
import java.util.Optional;

import com.planet.api.document.Planet;

public interface PlanetService {
	Optional<Planet> add(Planet planet);

	Optional<Planet> update(Planet planet);

	Optional<Planet> findById(String id);

	Optional<Planet> findByName(String name);
	
	List<Planet> findAll();

	void delete(String id);

}
