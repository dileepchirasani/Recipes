package org.spring.recipe.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.services.RecipeService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class HomeControllerTest {

	HomeController homeController;
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	homeController = new HomeController(recipeService);
	}
	
	@Test
	public void MockMVCTest() {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).
			andExpect(view().name("index"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getHome() {
		
		//GIVEN
		Set<Recipe> recipeSet = new HashSet<Recipe>();
		Recipe recipe =  new Recipe();
		recipe.setId(2L);
		recipeSet.add(recipe);
		recipeSet.add(new Recipe());
		// WHEN
		when(recipeService.getRecipes()).thenReturn(recipeSet);
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		// THEN
		String pageName = homeController.getHome(model);
		assertEquals("index", pageName);
		verify(recipeService, times(1)).getRecipes();
		//verify(model, times(1)).addAttribute(Mockito.eq("recipes"), Mockito.anySet());
		verify(model, times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
		verify(model, times(1)).addAttribute(Mockito.eq("message"), Mockito.anyString());
		assertEquals(2, argumentCaptor.getValue().size());
	}
}
