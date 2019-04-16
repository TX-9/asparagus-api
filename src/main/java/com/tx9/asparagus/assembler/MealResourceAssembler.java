package com.tx9.asparagus.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.tx9.asparagus.controller.MealController;
import com.tx9.asparagus.model.Meal;

@Component
public class MealResourceAssembler implements ResourceAssembler<Meal, Resource<Meal>> {

	@Override
	public Resource<Meal> toResource(Meal meal) {
		return new Resource<> (meal,
				linkTo(methodOn(MealController.class).one(meal.getId())).withSelfRel(),
				linkTo(methodOn(MealController.class).all()).withRel("meal"));
	}

}
