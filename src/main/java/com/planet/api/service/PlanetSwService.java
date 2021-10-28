package com.planet.api.service;

import com.planet.api.document.ResultApiSW;

public interface PlanetSwService {
	ResultApiSW findPlanetByName(String name);
}
