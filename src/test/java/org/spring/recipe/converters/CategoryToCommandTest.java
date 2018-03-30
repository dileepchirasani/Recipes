package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.model.Category;

public class CategoryToCommandTest {

	CategoryToCommand categoryConverter;
	
	@Before
	public void setUp() {
		this.categoryConverter = new CategoryToCommand();
	}
	
	@Test
	public void convertNull() {
		assertNull(categoryConverter.convert(null));
	}
	@Test
	public void convertEmptyInstance() {
		assertNotNull(categoryConverter.convert(new Category()));
	}
	@Test
	public void convert() {
		//GIVEN
		Category category = new Category("American");
		category.setId(1L);
		//WHEN
		CategoryCommand command = categoryConverter.convert(category);
		assertEquals(category.getDescription(),command.getDescription());
		assertEquals(category.getId(), command.getId());
		
	}
}
