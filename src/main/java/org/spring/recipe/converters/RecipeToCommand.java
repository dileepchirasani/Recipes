package org.spring.recipe.converters;

import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.model.Category;
import org.spring.recipe.model.Ingredient;
import org.spring.recipe.model.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToCommand implements Converter<Recipe, RecipeCommand>{

	private final NotesToCommand notesConverter;
	private final CategoryToCommand categoryConverter;
	private final IngredientToCommand ingredientConverter;
	public RecipeToCommand(NotesToCommand notesToCommand,
			CategoryToCommand categoryToCommand,
			IngredientToCommand ingredientToCommand) {
		this.notesConverter = notesToCommand;
		this.categoryConverter = categoryToCommand;
		this.ingredientConverter = ingredientToCommand;
	}
	
	@Override
	public RecipeCommand convert(Recipe recipe) {
		if(recipe == null ) {
			return null;
		}
		final RecipeCommand command = new RecipeCommand();
		command.setId(recipe.getId());
        command.setCookTime(recipe.getCookTime());
        command.setPrepTime(recipe.getPrepTime());
        command.setDescription(recipe.getDescription());
        command.setDifficulty(recipe.getDifficulty());
        command.setDirections(recipe.getDirections());
        command.setServes(recipe.getServes());
        command.setUrl(recipe.getUrl());
        command.setNotes(notesConverter.convert(recipe.getNotes()));
        
        if( recipe.getCategories() != null && !recipe.getCategories().isEmpty()) {
        	recipe.getCategories().forEach( (Category category) -> command.getCategories().add(categoryConverter.convert(category)));
        }
        if( recipe.getIngredients()!= null && !recipe.getIngredients().isEmpty()) {
        	recipe.getIngredients().forEach((Ingredient ingredient) -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        
        return command;
	}

}
