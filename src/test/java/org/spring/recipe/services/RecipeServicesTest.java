package org.spring.recipe.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.repository.RecipeRepository;

public class RecipeServicesTest {

	RecipeService recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeService(recipeRepository);
	}
	
	@Test
	public void getRecipes() {
		//given
		Set<Recipe> recipeSet = new HashSet<Recipe>();
		Recipe recipe =  new Recipe();
		recipe.setId(2L);
		recipeSet.add(recipe);
		
		// when
		when(recipeService.getRecipes()).thenReturn(recipeSet);
		
		//then
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll(); 
	}
}
