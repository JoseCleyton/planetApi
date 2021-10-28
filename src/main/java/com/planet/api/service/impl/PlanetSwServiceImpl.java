package com.planet.api.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.planet.api.document.ResultApiSW;
import com.planet.api.service.PlanetSwService;

@Service
public class PlanetSwServiceImpl implements PlanetSwService {
	@Value("${urlSwApi}")
	private String urlSwApi;

	@Override
	public ResultApiSW findPlanetByName(String name) {
		String fooResourceUrl = urlSwApi + "?search=" + name;
		ResponseEntity<ResultApiSW> response = new RestTemplate().exchange(fooResourceUrl, HttpMethod.GET, null,
				ResultApiSW.class);
		return response.getBody();
	}

}
