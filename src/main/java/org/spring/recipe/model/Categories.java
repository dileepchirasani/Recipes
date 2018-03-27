package org.spring.recipe.model;

public enum Categories {
	AMERICAN("American"), ITALIAN("Italian"), MEXICAN("Mexican"), FAST_FOOD("Fast Food");
	Categories(String name) {
		this.displayName = name;
	}
	String displayName;
	
	public String displayName() {
		return this.displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
	
}
