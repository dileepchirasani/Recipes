package org.spring.recipe.model;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class CategoryTest {
	Category category;
	
	@Before
	public void Setup() {
		category = new Category();
	}
	
	@Test
	public void getId() throws Exception{
		Long idValue = 4l;
		category.setId(idValue);
		assertEquals(4l, (long)category.getId());
	}
	
	@Test
	public void getDescription() throws Exception{
		
	}
}
