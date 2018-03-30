package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
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
import org.spring.recipe.converters.IngredientToCommand;
import org.spring.recipe.converters.UnitOfMeasureToCommand;
import org.spring.recipe.model.Ingredient;
import org.spring.recipe.model.UnitOfMeasure;

public class IngredientToCommandTest {

	@Mock
	UnitOfMeasureToCommand uomConverter;
	
	IngredientToCommand ingredientConverterMocked;
	IngredientToCommand ingredientConverter;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ingredientConverterMocked = new IngredientToCommand(uomConverter);
		ingredientConverter = new IngredientToCommand(new UnitOfMeasureToCommand());
	}
	
	@Test
	public void convert() throws Exception{
		//given
		IngredientCommand command = this.ingredientConverter.convert(null);
		assertEquals(null, command);
		Ingredient ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setAmount(new BigDecimal("20"));
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(1L);
		ingredient.setUom(new UnitOfMeasure("Teaspoon"));
		ingredient.getUom().setId(2L);
		
		//when
		command = this.ingredientConverterMocked.convert(ingredient);
		
		//then
		verify(uomConverter, times(1)).convert(Mockito.any());
		assertEquals(ingredient.getId(), command.getId());
		
		//assertEquals(uomCommand.getId(), (Long)command.getUom().getId());
	}
	
	@Test
	public void testWithUOMInstance() {
		//given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(2L);
		ingredient.setAmount(new BigDecimal("20"));
		ingredient.setDescription("Onion Pieces");
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(1L);
		uom.setDescription("Tablespoon");
		ingredient.setUom(uom);
		//when
		IngredientCommand command = ingredientConverter.convert(ingredient);
		
		//then
		assertEquals(ingredient.getId(), command.getId());
		assertEquals(ingredient.getAmount(), command.getAmount());
		assertEquals(ingredient.getDescription(), command.getDescription());
		assertEquals(uom.getId(), command.getUom().getId());
	}
}
