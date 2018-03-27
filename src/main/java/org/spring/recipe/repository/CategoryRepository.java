package org.spring.recipe.repository;

import java.util.Optional;

import org.spring.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
}
