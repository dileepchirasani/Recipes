package org.spring.recipe.converters;


import org.spring.recipe.command.CategoryCommand;
import org.spring.recipe.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCommand implements Converter<Category, CategoryCommand>{

	@Override
	public CategoryCommand convert(Category category) {
		// TODO Auto-generated method stub
		if(category == null) {
			return null;
		}
		final CategoryCommand command = new CategoryCommand();
		command.setId(category.getId());
		command.setDescription(category.getDescription());
		return command;
	}
	
}
