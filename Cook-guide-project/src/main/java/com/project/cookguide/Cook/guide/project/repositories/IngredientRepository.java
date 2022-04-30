package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
