package org.spring.recipe.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.spring.recipe.command.NotesCommand;
import org.spring.recipe.model.Notes;

public class CommandToNotesTest {

	public static final Long NOTES_ID = 1L;
	public static final String NOTES = "ADFSJL AJJDSFLAJDFL LAFJD ALSDFJLADSF LAJDFL ALFALSDF JLASDFJLAJFLAJSFDLJALDJFLAJSLDFJAL";

	CommandToNotes notesConverter;
	
	@Before
	public void setUp() {
	 notesConverter = new CommandToNotes();
	}
	
	@Test
	public void convertNull() {
		assertNull(notesConverter.convert(null));
	}
	
	@Test
	public void convertEmptyInstance() {
		assertNotNull(notesConverter.convert(new NotesCommand()));
	}
	@Test
	public void convert() {
		// GIVEN
		NotesCommand command = new NotesCommand();
		command.setId(NOTES_ID);
		command.setNotes(NOTES);
		
		//WHEN
		
		Notes notes = notesConverter.convert(command);
		assertEquals(NOTES_ID, notes.getId());
		assertEquals(NOTES, notes.getNotes());
		
	}
}
