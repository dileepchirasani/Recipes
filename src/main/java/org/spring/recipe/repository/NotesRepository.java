package org.spring.recipe.repository;

import org.spring.recipe.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {

}
