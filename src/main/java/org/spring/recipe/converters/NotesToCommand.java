package org.spring.recipe.converters;

import org.spring.recipe.command.NotesCommand;
import org.spring.recipe.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToCommand implements Converter<Notes, NotesCommand>{

	@Override
	public NotesCommand convert(Notes notes) {
		if(notes == null ) {
			return null;
		}
		final NotesCommand command = new NotesCommand();
		command.setId(notes.getId());
		command.setNotes(notes.getNotes());
		
		return command;
	}
	
	
}
