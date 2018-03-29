package org.spring.recipe.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.services.RecipeService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class RecipeControllerTest {

	
	RecipeController recipeController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}
	
	// Wrong way to test a controller method which expects parameter
	// @Test
	public void showRecipe_BKP() {
		String returnVal = "recipe/show";
		when(recipeController.showRecipe(Mockito.anyString(), Mockito.any())).thenReturn(returnVal);
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOp = Optional.of(recipe);
		when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipeOp);
		
		String value = recipeController.showRecipe("1", model);
		assertEquals(value, "recipe/show");
		ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
		
		verify(model, times(1)).addAttribute(Mockito.anyString(), argumentCaptor.capture());
		verify(recipeService, times(1)).getRecipeById(Mockito.anyLong());
		//
		System.out.println(argumentCaptor.getValue());
		//assertEquals(recipe, argumentCaptor.getValue());
	}
	
	@Test
	public void showRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(Optional.of(recipe));
		mockMvc.perform(get("/recipe/show/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/show")).andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}
}
