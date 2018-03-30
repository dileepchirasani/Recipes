package org.spring.recipe.converters;

import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommandToCategory implements Converter<CategoryCommand, Category>{

	@Override
	public Category convert(CategoryCommand command) {
		if(command == null) {
			return null;
		}
		final Category category = new Category();
		category.setId(command.getId());
		category.setDescription(command.getDescription());
		return category;
	}
	
}
