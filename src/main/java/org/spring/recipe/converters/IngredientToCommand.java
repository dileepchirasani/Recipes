package org.spring.recipe.converters;

import org.spring.recipe.command.IngredientCommand;
import org.spring.recipe.command.UnitOfMeasureCommand;
import org.spring.recipe.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToCommand uomConverter;
	public IngredientToCommand(UnitOfMeasureToCommand uomToCommand) {
		this.uomConverter = uomToCommand;
	}
	@Override
	public IngredientCommand convert(Ingredient ingredient) {
		if(ingredient == null) {
			return null;
		}
		final IngredientCommand command = new IngredientCommand();
		command.setId(ingredient.getId());
		command.setAmount(ingredient.getAmount());
		command.setDescription(ingredient.getDescription());
		command.setUom(uomConverter.convert(ingredient.getUom()));
		return command;
	}

}
