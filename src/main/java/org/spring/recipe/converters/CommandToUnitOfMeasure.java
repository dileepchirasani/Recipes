package org.spring.recipe.converters;

import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.model.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand command) {
		if(command == null) {
			return null;
		}
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(command.getId());
		uom.setDescription(command.getDescription());
		return uom;
	}

}
