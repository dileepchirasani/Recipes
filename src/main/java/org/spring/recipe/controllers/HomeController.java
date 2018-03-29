package org.spring.recipe.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import org.spring.recipe.bootstrap.RecipeBootstrap;
import org.spring.recipe.model.Category;
import org.spring.recipe.model.UnitOfMeasure;
import org.spring.recipe.repository.CategoryRepository;
import org.spring.recipe.repository.RecipeRepository;
import org.spring.recipe.repository.UnitOfMeasureRepository;
import org.spring.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	RecipeService recipeService;
	RecipeRepository recipeRepo;
	
	public HomeController(RecipeService recipeService ) {
		this.recipeService = recipeService;
	}
	
	@RequestMapping({"","/","/index","index"})
	public String getHome(Model model) {
		// this.recipeBootstrap.saveRecipe();
		model.addAttribute("recipes",this.recipeService.getRecipes());
		
		model.addAttribute("message","Hello from Controller haha");
		return "index";
	}
}
