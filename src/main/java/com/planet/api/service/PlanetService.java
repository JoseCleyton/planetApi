package com.planet.api.service;

import java.util.List;
import java.util.Optional;

import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourcesNotFoundException;

public interface PlanetService {
	Planet add(Planet planet) throws PayloadNotConsistentException;

	Planet update(Planet planet) throws ResourcesNotFoundException;

	Optional<Planet> findById(String id) throws ResourcesNotFoundException;

	List<Planet> findAll();

	void delete(String id) throws ResourcesNotFoundException;

}
