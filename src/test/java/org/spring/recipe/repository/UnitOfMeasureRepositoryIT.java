package org.spring.recipe.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.recipe.model.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository uomRepo;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	@DirtiesContext
	public void findByDescription() throws Exception {
		Optional<UnitOfMeasure> uom = uomRepo.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uom.get().getDescription());
	}
	
	@Test
	public void findByDescriptionForCup() throws Exception {
		Optional<UnitOfMeasure> uom = uomRepo.findByDescription("Cup");
		assertEquals("Cup", uom.get().getDescription());
	}
}
