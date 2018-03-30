package org.spring.recipe.services;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.converters.CommandToRecipe;
import org.spring.recipe.converters.RecipeToCommand;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RecipeService {

	private RecipeRepository recipeRepo;
	private RecipeToCommand recipeConverter;
	private CommandToRecipe recipeCommandConverter;
	
	public RecipeService(RecipeRepository recipeRepository,
			RecipeToCommand recipeConverter,
			CommandToRecipe recipeCommandConverter) {
		this.recipeRepo= recipeRepository;
		this.recipeConverter = recipeConverter;
		this.recipeCommandConverter = recipeCommandConverter;
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

	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe recipedetached = recipeCommandConverter.convert(recipeCommand);
		Recipe recipeSaved = this.recipeRepo.save(recipedetached);
		return this.recipeConverter.convert(recipeSaved);
	}
	
}
