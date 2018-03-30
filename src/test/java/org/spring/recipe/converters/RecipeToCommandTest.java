package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.model.Category;
import org.spring.recipe.model.Difficulty;
import org.spring.recipe.model.Ingredient;
import org.spring.recipe.model.Notes;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.model.UnitOfMeasure;

public class RecipeToCommandTest {

	RecipeToCommand recipeConverter;
	
	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String URL = "Some URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;
	
	@Before
	public void setUp() {
		recipeConverter = new RecipeToCommand(new NotesToCommand(), new CategoryToCommand(), new IngredientToCommand(new UnitOfMeasureToCommand()));
	}
	
	@Test
	public void convertNull() {
		assertNull(recipeConverter.convert(null));
	}
	
	@Test
	public void convertEmpty() {
		assertNotNull(recipeConverter.convert(new Recipe()));
	}
	
	@Test
	public void convert() {
		//GIVEN
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServes(SERVINGS);
        recipe.setUrl(URL);
        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        notes.setNotes("aDSDFSDF DSSDF DSFSDFSDFSDF DSFSDF");
        recipe.setNotes(notes);
        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(1L);
        ingredient.setUom(uom);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        ingredient2.setUom(uom2);
        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);
        
        // WHEN
        RecipeCommand command = recipeConverter.convert(recipe);
        
        // THEN
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServes());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
	}
}
