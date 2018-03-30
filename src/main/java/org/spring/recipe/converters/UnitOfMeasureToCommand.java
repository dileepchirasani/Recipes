package org.spring.recipe.converters;

import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure uom) {
		if(uom == null) {
			return null;
		}
		final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(uom.getId());
		command.setDescription(uom.getDescription());
		return command;
	}

	
}
