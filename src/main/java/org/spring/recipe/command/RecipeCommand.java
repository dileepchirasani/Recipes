package org.spring.recipe.command;

import java.util.HashSet;
import java.util.Set;
import org.spring.recipe.model.Difficulty;


public class RecipeCommand {

	private Long id;
	private String name;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servTime;
	private Integer serves;
	private String url;
	
	private String directions;
	
	private Set<IngredientCommand> ingredients = new HashSet<IngredientCommand>();
	
	private NotesCommand notes;
	
	private Difficulty difficulty;
	
	private Set<CategoryCommand> categories = new HashSet<CategoryCommand>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getServTime() {
		return servTime;
	}

	public void setServTime(Integer servTime) {
		this.servTime = servTime;
	}

	public Integer getServes() {
		return serves;
	}

	public void setServes(Integer serves) {
		this.serves = serves;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public Set<IngredientCommand> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientCommand> ingredients) {
		this.ingredients = ingredients;
	}

	public NotesCommand getNotes() {
		return notes;
	}

	public void setNotes(NotesCommand notes) {
		this.notes = notes;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Set<CategoryCommand> getCategories() {
		return categories;
	}

	public void setCategories(Set<CategoryCommand> categories) {
		this.categories = categories;
	}
	
}
