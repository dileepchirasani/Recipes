package org.spring.recipe.controllers;

import java.util.Optional;

import org.spring.recipe.model.Recipe;
import org.spring.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	
	@RequestMapping("/show/{id}")
	public String showRecipe(@PathVariable String id, Model model) {
		Optional<Recipe> recipeOp=this.recipeService.getRecipeById(Long.valueOf(id));
		model.addAttribute("recipe",recipeOp.get());
		return "recipe/show";
	}
}
