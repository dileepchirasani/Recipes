package org.spring.recipe.services;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.spring.recipe.model.Recipe;
import org.spring.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;


@Service
public class RecipeService {

	private RecipeRepository recipeRepo;
	
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepo= recipeRepository;
	}
	
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<Recipe>();
		this.recipeRepo.findAll().forEach(recipes :: add);
		return recipes;
	}
	
	public Optional<Recipe> getRecipeById(Long id) {
		Optional<Recipe> recipeOp = this.recipeRepo.findById(id);
		return recipeOp;
	}
}
