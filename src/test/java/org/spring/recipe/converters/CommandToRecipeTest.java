package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.command.IngredientCommand;
import org.spring.recipe.command.NotesCommand;
import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.model.Difficulty;
import org.spring.recipe.model.Recipe;

public class CommandToRecipeTest {
	public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final Integer SERV_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVES = Integer.valueOf("3");
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
	 CommandToRecipe recipeConverter;
	 
	 @Before
	 public void setUp() {
		 this.recipeConverter = new CommandToRecipe(new CommandToNotes(), new CommandToCategory(), new CommandToIngredient(new CommandToUnitOfMeasure()));
	 }
	 
	 @Test
	 public void convertNull() {
		 assertNull(recipeConverter.convert(null));
	 }
	 
	 @Test
	 public void convertEmptyInstance() {
		 assertNotNull(recipeConverter.convert(new RecipeCommand()));
	 }
	 
	 @Test
	 public void convert() {
		 RecipeCommand command = new RecipeCommand();
		 command.setId(RECIPE_ID);
		 command.setCookTime(COOK_TIME);
		 command.setPrepTime(PREP_TIME);
		 command.setServTime(SERV_TIME);
		 command.setDescription(DESCRIPTION);
		 command.setDirections(DIRECTIONS);
		 command.setUrl(URL);
		 
		 IngredientCommand ingCommand1 = new IngredientCommand();
		 ingCommand1.setId(INGRED_ID_1);
		 IngredientCommand ingCommand2 = new IngredientCommand();
		 ingCommand2.setId(INGRED_ID_2);
		 command.getIngredients().add(ingCommand1);
		 command.getIngredients().add(ingCommand2);
		 CategoryCommand catCommand1 = new CategoryCommand();
		 catCommand1.setId(CAT_ID_1);
		 CategoryCommand catCommand2 = new CategoryCommand();
		 catCommand2.setId(CAT_ID2);
		 command.getCategories().add(catCommand1);
		 command.getCategories().add(catCommand2);
		 NotesCommand notesCommand = new NotesCommand();
		 notesCommand.setId(NOTES_ID);
		 command.setNotes(notesCommand);
		 Recipe recipe = recipeConverter.convert(command);
		 
		 assertEquals(command.getId(), recipe.getId());
		 assertEquals(command.getCookTime(), recipe.getCookTime());
		 assertEquals(command.getPrepTime(), recipe.getPrepTime());
		 assertEquals(command.getServTime(), recipe.getServTime());
		 assertEquals(command.getServes(), recipe.getServes());
		 assertEquals(command.getNotes().getId(), recipe.getNotes().getId());
		 assertEquals(2, recipe.getCategories().size());
		 assertEquals(2, recipe.getIngredients().size());
		 assertEquals(command.getUrl(), recipe.getUrl());
		 assertEquals(command.getDifficulty(), recipe.getDifficulty());
		 assertEquals(recipe, recipe.getNotes().getRecipe());
	 }
}
