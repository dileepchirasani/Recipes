package org.spring.recipe.converters;

import org.spring.recipe.command.NotesCommand;
import org.spring.recipe.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToNotes implements Converter<NotesCommand, Notes> {

	@Override
	public Notes convert(NotesCommand command) {
		if(command == null) {
			return null;
		}
		final Notes notes = new Notes();
		notes.setId(command.getId());
		notes.setNotes(command.getNotes());
		return notes;
	}
	

}
