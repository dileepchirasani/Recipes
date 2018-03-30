package org.spring.recipe.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.recipe.command.RecipeCommand;
import org.spring.recipe.converters.CommandToRecipe;
import org.spring.recipe.converters.RecipeToCommand;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.repository.CategoryRepository;
import org.spring.recipe.repository.IngredientRepository;
import org.spring.recipe.repository.NotesRepository;
import org.spring.recipe.repository.RecipeRepository;
import org.spring.recipe.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest // Alternative for @DataJpaTest. SpringBootTest will contain all the configurations for database. 
// while @DataJpaTest is lightweight
public class RecipeServicesTest {

	RecipeService recipeServiceMocked;

	public static final String NEW_RECIPE_DESCRIPTION = "NEW DESCRIPTION FOR EXISTING RECIPE";
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeToCommand recipeConverter;
	
	@Autowired
	CommandToRecipe recipeCommandConverter;
	
	@Mock
	RecipeRepository recipeRepositoryMock;
	
	@Mock
	RecipeToCommand recipeConverterMock;
	
	@Mock
	CommandToRecipe recipeCommandConverterMock;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeServiceMocked = new RecipeService(recipeRepositoryMock, recipeConverterMock, recipeCommandConverterMock);
		
	}
	
	@Test
	public void getRecipes() {
		//given
		Set<Recipe> recipeSet = new HashSet<Recipe>();
		Recipe recipe =  new Recipe();
		recipe.setId(2L);
		recipeSet.add(recipe);
		
		// when
		when(recipeServiceMocked.getRecipes()).thenReturn(recipeSet);
		
		//then
		Set<Recipe> recipes = recipeServiceMocked.getRecipes();
		assertEquals(recipes.size(), 1);
		verify(recipeRepositoryMock, times(1)).findAll(); 
	}
	
	@Test
	public void getRecipeById() {
		Recipe recipeMock = new Recipe();
		recipeMock.setId(1L);
		Optional<Recipe> recipeOp = Optional.of(recipeMock);
		
		when(recipeRepositoryMock.findById(Mockito.anyLong())).thenReturn(recipeOp);
		when(recipeServiceMocked.getRecipeById(Mockito.anyLong())).thenReturn(recipeOp);
		Optional<Recipe> recipe = recipeServiceMocked.getRecipeById(1L);
		verify(recipeRepositoryMock, times(1)).findById(Mockito.anyLong());
		verify(recipeRepositoryMock, Mockito.never()).findAll();
	}
	
	@Transactional
	@Test
	public void testSaveDescription() {
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe recipeToUpdate = recipes.iterator().next();
		//System.out.println("-------------------------------------------------------"+recipeToUpdate.toString());
		//System.out.println("--=============================================================================================================-----");
		
		RecipeCommand recipeCommand = recipeConverter.convert(recipeToUpdate);
		
		recipeCommand.setDescription(NEW_RECIPE_DESCRIPTION);
		//when
		RecipeCommand recipeCRecieved = recipeService.saveRecipeCommand(recipeCommand);
	
		// then
		assertEquals(NEW_RECIPE_DESCRIPTION, recipeCRecieved.getDescription());
	}
	
}
