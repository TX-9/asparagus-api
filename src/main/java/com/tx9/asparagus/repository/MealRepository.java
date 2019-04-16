package com.tx9.asparagus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tx9.asparagus.model.Meal;

public interface MealRepository extends JpaRepository<Meal, Long>{

}
