package com.tx9.asparagus.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tx9.asparagus.assembler.MealResourceAssembler;
import com.tx9.asparagus.exception.MealNotFoundException;
import com.tx9.asparagus.model.Meal;
import com.tx9.asparagus.repository.MealRepository;

@RestController
public class MealController {
	private final MealRepository repository;
	private final MealResourceAssembler assembler;
	
	public MealController(MealRepository repository, MealResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/meal")
	public Resources<Resource<Meal>> all() {
		List<Resource<Meal>> meal = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(meal, 
				linkTo(methodOn(MealController.class).all()).withSelfRel());
	}
	
	@GetMapping("/meal/{id}")
	public Resource<Meal> one(@PathVariable Long id) {
		Meal meal = repository.findById(id)
				.orElseThrow(() -> new MealNotFoundException(id));
		return assembler.toResource(meal);
	}
	
	@PostMapping("/meal")
	ResponseEntity<?> newMeal(@RequestBody Meal newMeal) throws URISyntaxException {
		Resource<Meal> resource = assembler.toResource(repository.save(newMeal));
		return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
	}
	
	@PutMapping("/meal/{id}")
	ResponseEntity<?> replaceMeal(@RequestBody Meal newMeal, @PathVariable Long id) throws URISyntaxException{
		Meal updatedMeal = repository.findById(id)
				.map(meal -> {
					meal.setName(newMeal.getName());
					return repository.save(meal);
				})
				.orElseGet(() -> {
					newMeal.setId(id);
					return repository.save(newMeal);
				});
		Resource<Meal> resource = assembler.toResource(updatedMeal);
		
		return ResponseEntity.created(new URI(resource.getId().expand().getHref()))
				.body(resource);	
	}
	
	@DeleteMapping("meal/{id}")
	ResponseEntity<?> deleteMeal(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
