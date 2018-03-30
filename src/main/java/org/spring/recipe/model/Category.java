package org.spring.recipe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Category {

	public Category(String description) {
		this.description = description;
	}
	public Category() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	
	@ManyToMany(mappedBy="categories")
	private Set<Recipe> recipes = new HashSet<Recipe>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Recipe> getRecipes(){
		return this.recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", description=" + description + ", recipes=" + recipes + "]";
	}
	
	
}
