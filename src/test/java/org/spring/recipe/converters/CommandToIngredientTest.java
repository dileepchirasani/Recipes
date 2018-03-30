package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.recipe.command.IngredientCommand;
import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.model.Ingredient;
import org.spring.recipe.model.UnitOfMeasure;

public class CommandToIngredientTest {

	
	public static final Long ING_ID = 1L;
	public static final String DESC = "Cloves of Garlic";
	public static final BigDecimal AMOUNT = new BigDecimal("20");
	public static final Long UOM_ID = new Long(2L);
	@Mock
	CommandToUnitOfMeasure uomConverter;
	
	CommandToIngredient ingredientConverterMocked;
	CommandToIngredient ingredientConverter;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ingredientConverterMocked = new CommandToIngredient(uomConverter);
		ingredientConverter = new CommandToIngredient(new CommandToUnitOfMeasure());
	}
	
	@Test
	public void convertNull() {
		assertNull(ingredientConverter.convert(null));
		
	}
	
	@Test
	public void convertEmptyInstance() {
		assertNotNull(ingredientConverter.convert(new IngredientCommand()));
	}
	
	@Test
	public void convertWithMockedUOM() {
		//GIVEN
		IngredientCommand command = new IngredientCommand();
		command.setId(ING_ID);
		command.setDescription(DESC);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);
		uomCommand.setDescription("Each");
		command.setUom(uomCommand);
		
		UnitOfMeasure uomMock = new UnitOfMeasure();
		uomMock.setId(uomCommand.getId());
		uomMock.setDescription(uomCommand.getDescription());
		// when
		when(uomConverter.convert(Mockito.any(UnitOfMeasureCommand.class))).thenReturn(uomMock);
		Ingredient ingredient = ingredientConverterMocked.convert(command);
		
		verify(uomConverter, times(1)).convert(Mockito.any(UnitOfMeasureCommand.class));
		assertEquals(uomCommand.getId(), ingredient.getUom().getId());
		assertEquals(command.getId(),ingredient.getId());
		assertEquals(command.getAmount(),ingredient.getAmount());
		assertEquals(command.getDescription(), ingredient.getDescription());
		assertEquals(uomCommand.getDescription(), ingredient.getUom().getDescription());
	}
	
	
	@Test
	public void convert() {
		IngredientCommand command = new IngredientCommand();
		command.setId(ING_ID);
		command.setDescription(DESC);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);
		uomCommand.setDescription("Each");
		command.setUom(uomCommand);
		
		//when
		Ingredient ingredient = ingredientConverter.convert(command);
		
		// THEN
		assertEquals(uomCommand.getId(), ingredient.getUom().getId());
		assertEquals(command.getId(),ingredient.getId());
		assertEquals(command.getAmount(),ingredient.getAmount());
		assertEquals(command.getDescription(), ingredient.getDescription());
		assertEquals(uomCommand.getDescription(), ingredient.getUom().getDescription());
		
		
	}
}
