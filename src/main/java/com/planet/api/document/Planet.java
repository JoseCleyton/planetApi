package com.planet.api.document;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Planet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@NotNull(message = "Name cannot null")
	@NotBlank(message = "Name cannot blank")
	private String name;

	@NotNull(message = "Ground cannot null")
	@NotBlank(message = "Ground cannot blank")
	public String terrain;

	@NotNull(message = "Climate cannot null")
	@NotBlank(message = "Climate cannot blank")
	private String climate;

	private int numberOfApparitions;

	public Planet(String name, String terrain, String climate, int numberOfApparitions) {
		super();
		this.name = name;
		this.terrain = terrain;
		this.climate = climate;
		this.numberOfApparitions = numberOfApparitions;
	}

}
