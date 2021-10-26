package com.planet.api.document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planet {

	@Id
	private String id;
	@NotNull(message = "Name cannot null")
	@NotBlank(message = "Name cannot blank")
	private String name;
	
	@NotNull(message = "Ground cannot null")
	@NotBlank(message = "Ground cannot blank")
	private String ground;
	
	@NotNull(message = "Climate cannot null")
	@NotBlank(message = "Climate cannot blank")
	private String climate;
	
	private int numberOfApparitions;

	public Planet() {
		super();
	}

	public Planet(String id, String name, String ground, String climate, int numberOfApparitions) {
		super();
		this.id = id;
		this.name = name;
		this.ground = ground;
		this.climate = climate;
		this.numberOfApparitions = numberOfApparitions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public int getNumberOfApparitions() {
		return numberOfApparitions;
	}

	public void setNumberOfApparitions(int numberOfApparitions) {
		this.numberOfApparitions = numberOfApparitions;
	}

}
