package com.tx9.asparagus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Meal {
	private @Id @GeneratedValue Long id;
	private String name;
	
	public Meal(){}

	public Meal(String name) {
		this.name = name;
	}
	
	
}
