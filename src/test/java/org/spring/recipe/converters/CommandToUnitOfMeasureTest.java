package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.model.UnitOfMeasure;

public class CommandToUnitOfMeasureTest {

	public static final Long UOM_ID = 1L;
	public static final String DESCRIPTION = "Tablespoon";
	CommandToUnitOfMeasure uomConverter;
	
	@Before
	public void setUp() {
		uomConverter = new CommandToUnitOfMeasure();
	}
	
	@Test
	public void convertNull() {
		assertNull(uomConverter.convert(null));
	}
	
	@Test
	public void convertEmptyInstance() {
		assertNotNull(uomConverter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void convert() {
		//GIVEN
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);
		uomCommand.setDescription(DESCRIPTION);
		//WHEN
		UnitOfMeasure uom = uomConverter.convert(uomCommand);
		
		//THEN 
		assertEquals(UOM_ID, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
}
