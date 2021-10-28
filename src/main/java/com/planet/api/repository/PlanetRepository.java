package com.planet.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.planet.api.document.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
	Optional<Planet> findByName(String name);
}
