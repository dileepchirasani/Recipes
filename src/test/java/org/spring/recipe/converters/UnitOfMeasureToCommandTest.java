package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.converters.UnitOfMeasureToCommand;
import org.spring.recipe.model.UnitOfMeasure;

public class UnitOfMeasureToCommandTest {

	UnitOfMeasureToCommand uomConverter;
	
	@Before
	public void setUp() {
		this.uomConverter = new UnitOfMeasureToCommand();
	}
	
	@Test
	public void convert() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(1L);
		uom.setDescription("Teaspoon");
		UnitOfMeasureCommand command = this.uomConverter.convert(null);
		assertEquals(null, command);
		command = this.uomConverter.convert(uom);
		assertEquals(uom.getId(),command.getId());
		// assertEquals("Tablespoon",command.getDescription());
	}
}
