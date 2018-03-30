package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.model.Categories;
import org.spring.recipe.model.Category;

public class CommandToCategoryTest {

	public static final Long CAT_ID = 2L;
	public static final String DESC = "Indian";
	CommandToCategory categoryConverter;
	
	@Before
	public void setUp() {
		categoryConverter = new CommandToCategory();
	}
	
	@Test
	public void convertNull() {
		assertNull(categoryConverter.convert(null));
	}
	
	@Test
	public void convertEmptyInstance() {
		assertNotNull(categoryConverter.convert(new CategoryCommand()));
	}
	
	@Test
	public void convert() {
		CategoryCommand command = new CategoryCommand();
		command.setId(CAT_ID);
		command.setDescription(Categories.AMERICAN.displayName());
		
		//WHEN 
		Category category = categoryConverter.convert(command);
		
		// THEN
		assertEquals(Categories.AMERICAN.displayName(), category.getDescription());
		assertEquals(CAT_ID, category.getId());
	}
}
