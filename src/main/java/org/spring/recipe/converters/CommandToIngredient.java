package org.spring.recipe.converters;

import org.spring.recipe.command.IngredientCommand;
import org.spring.recipe.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToIngredient implements Converter<IngredientCommand, Ingredient>{

	private final CommandToUnitOfMeasure uomConverter;
	public CommandToIngredient(CommandToUnitOfMeasure commandToUnitOfMeasure) {
		this.uomConverter = commandToUnitOfMeasure;
	}
	@Override
	public Ingredient convert(IngredientCommand command) {
		if(command == null) {
			return null;
		}
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(command.getId());
		ingredient.setAmount(command.getAmount());
		ingredient.setDescription(command.getDescription());
		ingredient.setUom(uomConverter.convert(command.getUom()));
		return ingredient;
	}

	
}
