package com.planet.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planet.api.document.Planet;
import com.planet.api.exceptions.PayloadNotConsistentException;
import com.planet.api.exceptions.ResourcesNotFoundException;
import com.planet.api.response.Response;
import com.planet.api.service.PlanetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api/planet")
@Api(value = "API REST for Planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@GetMapping
	@ApiOperation(value = "List all Planets")
	public ResponseEntity<List<Planet>> findAll() {
		return new ResponseEntity<List<Planet>>(this.planetService.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "List one Planet By Id")
	public ResponseEntity<Planet> findById(@PathVariable String id) {
		return this.planetService.findById(id).map(planet -> new ResponseEntity<>(planet, HttpStatus.OK))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(params = "name")
	@ApiOperation(value = "List one Planet By Name")
	public ResponseEntity<Planet> findByName(@RequestParam(name = "name", defaultValue = "", required = true) String name) {
		return this.planetService.findByName(name).map(planet -> new ResponseEntity<>(planet, HttpStatus.OK))
				.orElse(ResponseEntity.notFound().build());
	}


	@PostMapping
	@ApiOperation(value = "Add one Planet")
	public ResponseEntity<Response<Planet>> add(@Valid @RequestBody Planet planet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errs = this.buildListErrs(bindingResult);
			return new ResponseEntity<Response<Planet>>(new Response<Planet>(errs), HttpStatus.BAD_REQUEST);
		}

		return this.planetService.add(planet)
				.map(p -> new ResponseEntity<Response<Planet>>(new Response<Planet>(p), HttpStatus.CREATED))
				.orElseThrow(PayloadNotConsistentException::new);
	}

	@PutMapping
	@ApiOperation(value = "Update one Planet")
	public ResponseEntity<Response<Planet>> update(@Valid @RequestBody Planet planet, BindingResult bindingResult)
			throws ResourcesNotFoundException {

		if (bindingResult.hasErrors()) {
			List<String> errs = this.buildListErrs(bindingResult);
			return new ResponseEntity<Response<Planet>>(new Response<Planet>(errs), HttpStatus.BAD_REQUEST);
		}

		return this.planetService.update(planet)
				.map(p -> new ResponseEntity<Response<Planet>>(new Response<Planet>(p), HttpStatus.ACCEPTED))
				.orElseThrow(ResourcesNotFoundException::new);
	}

	private List<String> buildListErrs(BindingResult bindingResult) {
		return bindingResult.getAllErrors().stream().map(err -> err.getDefaultMessage()).toList();
	}

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Delete one Planet By Id")
	public ResponseEntity<?> delete(@PathVariable String id) throws ResourcesNotFoundException {
		Optional<Planet> planet = this.planetService.findById(id);
		if (!planet.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		this.planetService.delete(id);
		return ResponseEntity.ok().build();

	}

}
