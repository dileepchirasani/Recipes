package org.spring.recipe.converters;

import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.command.IngredientCommand;
import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.model.Category;
import org.spring.recipe.model.Ingredient;
import org.spring.recipe.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CommandToNotes notesConverter;
	private final CommandToCategory categoryConverter;
	private final CommandToIngredient ingredientConverter;
	
	public  CommandToRecipe(CommandToNotes commandToNotes,
			CommandToCategory commandToCategory,
			CommandToIngredient commandToIngredient) {
		this.notesConverter = commandToNotes;
		this.categoryConverter = commandToCategory;
		this.ingredientConverter = commandToIngredient;
	}
	@Override
	public Recipe convert(RecipeCommand command) {
		if(command == null) {
			return null;
		}
		final Recipe recipe = new Recipe();
		recipe.setId(command.getId());
        recipe.setCookTime(command.getCookTime());
        recipe.setPrepTime(command.getPrepTime());
        recipe.setServTime(command.getServTime());
        recipe.setDescription(command.getDescription());
        recipe.setDifficulty(command.getDifficulty());
        recipe.setDirections(command.getDirections());
        recipe.setServes(command.getServes());
        
        recipe.setUrl(command.getUrl());
        
        if(command.getNotes() != null) {
        	recipe.setNotes(notesConverter.convert(command.getNotes()));
            recipe.getNotes().setRecipe(recipe);

        }
        if(command.getCategories() != null && !command.getCategories().isEmpty()) {
        	command.getCategories().forEach((CategoryCommand catCommand) -> { 
        		Category category = categoryConverter.convert(catCommand);
        		// I Have added the following line to add Recipe to category. need to check the usability
        		category.getRecipes().add(recipe);
        		recipe.getCategories().add(category);	
        	});
        }
        if(command.getIngredients() != null && !command.getCategories().isEmpty()) {
        	command.getIngredients().forEach((IngredientCommand ingCommand) -> {
        		Ingredient ingredient = ingredientConverter.convert(ingCommand);
        		// Need to check the usability of the following line of adding recipe to List. 
        		ingredient.setRecipe(recipe);
        		recipe.getIngredients().add(ingredient);
        		}
        	);
        }
        
		return recipe;
	}

}
