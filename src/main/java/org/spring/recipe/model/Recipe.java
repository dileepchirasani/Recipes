package org.spring.recipe.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {

	public Recipe() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servTime;
	private String source;
	private String url;
	
	@Lob
	private String directions;
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy="recipe")
	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	
	@Lob
	private Byte[] image;
	@OneToOne(cascade=CascadeType.ALL)
	private Notes notes;
	
	@Enumerated(value=EnumType.STRING)
	private Difficulty difficulty;
	
	@ManyToMany
	@JoinTable(name="recipe_category",
			joinColumns= @JoinColumn(name="recipe_id"),
			inverseJoinColumns = @JoinColumn(name="category_id")
			)
	private Set<Category> categories = new HashSet<Category>();
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the prepTime
	 */
	public Integer getPrepTime() {
		return prepTime;
	}
	/**
	 * @param prepTime the prepTime to set
	 */
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	/**
	 * @return the cookTime
	 */
	public Integer getCookTime() {
		return cookTime;
	}
	/**
	 * @param cookTime the cookTime to set
	 */
	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}
	/**
	 * @return the servTime
	 */
	public Integer getServTime() {
		return servTime;
	}
	/**
	 * @param servTime the servTime to set
	 */
	public void setServTime(Integer servTime) {
		this.servTime = servTime;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the directions
	 */
	public String getDirections() {
		return directions;
	}
	/**
	 * @param directions the directions to set
	 */
	public void setDirections(String directions) {
		this.directions = directions;
	}
	/**
	 * @return the image
	 */
	public Byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Byte[] image) {
		this.image = image;
	}
	/**
	 * @return the notes
	 */
	public Notes getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Difficulty getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
}
