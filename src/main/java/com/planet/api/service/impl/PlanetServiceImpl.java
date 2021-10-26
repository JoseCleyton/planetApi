package com.planet.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourceAlreadyExistsException;
import com.planet.api.exceptions.ResourcesNotFoundException;
import com.planet.api.repository.PlanetRepository;
import com.planet.api.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	@Override
	public Planet add(Planet planet) throws PayloadNotConsistentException {
		if (planet.getId() == null) {
			return this.planetRepository.save(planet);
		}
		throw new PayloadNotConsistentException();
	}

	@Override
	public Planet update(Planet planet) throws ResourcesNotFoundException {
		this.findById(planet.getId());
		return this.planetRepository.save(planet);
	}

	@Override
	public Optional<Planet> findById(String id) throws ResourcesNotFoundException {
		return this.planetRepository.findById(id).map(record -> Optional.of(record))
				.orElseThrow(ResourcesNotFoundException::new);
	}

	@Override
	public List<Planet> findAll() {
		return this.planetRepository.findAll();
	}

	@Override
	public void delete(String id) throws ResourcesNotFoundException {
		this.findById(id).ifPresent(record -> this.planetRepository.deleteById(record.getId()));

	}

}
