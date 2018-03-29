package org.spring.recipe.bootstrap;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.spring.recipe.model.Category;
import org.spring.recipe.model.Categories;
import org.spring.recipe.model.Difficulty;
import org.spring.recipe.model.Ingredient; 
import org.spring.recipe.model.Notes;
import org.spring.recipe.model.Recipe;
import org.spring.recipe.model.UOM;
import org.spring.recipe.model.UnitOfMeasure;
import org.spring.recipe.repository.CategoryRepository;
import org.spring.recipe.repository.RecipeRepository;
import org.spring.recipe.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final RecipeRepository recipeRepo;
	private final CategoryRepository categoryRepo;
	private final UnitOfMeasureRepository unitOfMeasureRepo;
	private Map<String, UnitOfMeasure> UomMap = new HashMap<String, UnitOfMeasure>();
	private Map<String, Category> categoryMap = new HashMap<String, Category>();
	public RecipeBootstrap( RecipeRepository recipeRepo,
								CategoryRepository categoryRepo,
								UnitOfMeasureRepository unitOfMeasureRepo ) {
		this.recipeRepo = recipeRepo;
		this.categoryRepo = categoryRepo;
		this.unitOfMeasureRepo = unitOfMeasureRepo;
	}
	private void populateUomMap() {
		
		Optional<UnitOfMeasure> optionalUomEach = unitOfMeasureRepo.findByDescription(UOM.Each.name());
		if(!optionalUomEach.isPresent()) {
			throw new RuntimeException("Expected UOM Each Not Found");
		}
		Optional<UnitOfMeasure> optionalUomTablespoon = unitOfMeasureRepo.findByDescription(UOM.Tablespoon.name());
		if(!optionalUomTablespoon.isPresent()) {
			throw new RuntimeException("Expected UOM Tablespoon Not Found");
		}
		Optional<UnitOfMeasure> optionalUomTeaspoon = unitOfMeasureRepo.findByDescription(UOM.Teaspoon.name());
		if(!optionalUomTeaspoon.isPresent()) {
			throw new RuntimeException("Expected UOM Teaspoon Not Found");
		}
		Optional<UnitOfMeasure> optionalUomDash = unitOfMeasureRepo.findByDescription(UOM.Dash.name());
		if(!optionalUomDash.isPresent()) {
			throw new RuntimeException("Expected UOM Dash Not Found");
		}
		Optional<UnitOfMeasure> optionalUomPint = unitOfMeasureRepo.findByDescription(UOM.Pint.name());
		if(!optionalUomPint.isPresent()) {
			throw new RuntimeException("Expected UOM Pint Not Found");
		}
		Optional<UnitOfMeasure> optionalUomCup = unitOfMeasureRepo.findByDescription(UOM.Cup.name());
		if(!optionalUomCup.isPresent()) {
			throw new RuntimeException("Expected UOM Cup Not Found");
		}
		
		this.UomMap.put(UOM.Each.name(), optionalUomEach.get());
		this.UomMap.put(UOM.Tablespoon.name(), optionalUomTablespoon.get());
		this.UomMap.put(UOM.Teaspoon.name(), optionalUomTeaspoon.get());
		this.UomMap.put(UOM.Pint.name(), optionalUomPint.get());
		this.UomMap.put(UOM.Cup.name(), optionalUomCup.get());
		this.UomMap.put(UOM.Dash.name(), optionalUomDash.get());
		
	}
	private Recipe getGuacamoleRecipe() {
		Recipe GuacRecipe= new Recipe();
		GuacRecipe.setCookTime(5);
		GuacRecipe.setServTime(20);
		GuacRecipe.setPrepTime(10);
		GuacRecipe.setName("Guacamole");
		Category category = this.getCategory(Categories.MEXICAN);
		category.getRecipes().add(GuacRecipe);
		GuacRecipe.getCategories().add(category);
		GuacRecipe.setServes("4");
		GuacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		GuacRecipe.setDescription("Guacamole, a dip made from avocados, is originally from Mexico."
				+ " The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");
		GuacRecipe.setDifficulty(Difficulty.EASY);
		GuacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\r\n" + 
				"\r\n" + 
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n" + 
				"\r\n" + 
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n" + 
				"\r\n" + 
				"4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\r\n" + 
				"\r\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\r\n" + 
				"\r\n");
		Notes GuacNotes = new Notes();
		GuacNotes.setNotes("Variations\r\n" + 
				"\r\n" + 
				"For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\r\n" + 
				"\r\n" + 
				"Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\r\n" + 
				"\r\n" + 
				"The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\r\n" + 
				"\r\n" + 
				"To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
		GuacNotes.setRecipe(GuacRecipe);
		GuacRecipe.setNotes(GuacNotes);
		GuacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal("2"), this.getUomValue(UOM.Each), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("Kosher Salt", new BigDecimal(".5"), this.getUomValue(UOM.Teaspoon), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("lemon Juice", new BigDecimal("1"), this.getUomValue(UOM.Tablespoon), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("Green Onion", new BigDecimal(".25"), this.getUomValue(UOM.Cup), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal("2"), this.getUomValue(UOM.Each), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("cilantro Leaves and Stems chopped", new BigDecimal("2"), this.getUomValue(UOM.Tablespoon), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("Black pepper", new BigDecimal("1"), this.getUomValue(UOM.Dash), GuacRecipe));
		GuacRecipe.getIngredients().add(new Ingredient("ripe tomato", new BigDecimal(".5"), this.getUomValue(UOM.Each), GuacRecipe));
		return GuacRecipe;
	}
	private Recipe getChickenTacos() {
		Recipe chickenTacos =  new Recipe();
		chickenTacos.setCookTime(20);
		chickenTacos.setServTime(0);
		chickenTacos.setPrepTime(10);
		chickenTacos.setName("Spicy Grilled Chicken Tacos");
		Category category = this.getCategory(Categories.MEXICAN);
		category.getRecipes().add(chickenTacos);
		chickenTacos.getCategories().add(category);
		chickenTacos.setServes("3");
		chickenTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
		chickenTacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes."
				+ " Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
		chickenTacos.setDifficulty(Difficulty.MODERATE);
		chickenTacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\r\n" + 
				"\r\n" + 
				"2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n" + 
				"\r\n" + 
				"Set aside to marinate while the grill heats and you prepare the rest of the toppings.\r\n" + 
				"\r\n" + 
				"3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\r\n" + 
				"\r\n" + 
				"4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\r\n" + 
				"\r\n" + 
				"Wrap warmed tortillas in a tea towel to keep them warm until serving.\r\n" + 
				"\r\n" + 
				"5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
		Notes notes = new Notes();
		notes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n" + 
				"\r\n" + 
				"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\r\n" + 
				"\r\n" + 
				"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\r\n" + 
				"\r\n" + 
				"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!"
				+"The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\r\n" + 
				"\r\n" + 
				"I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\r\n" + 
				"\r\n" + 
				"Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\r\n" + 
				"\r\n" + 
				"You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");
		notes.setRecipe(chickenTacos);
		chickenTacos.setNotes(notes);
		chickenTacos.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal("2"), this.getUomValue(UOM.Tablespoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("dried oregano", new BigDecimal("1"), this.getUomValue(UOM.Teaspoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("dried cumin", new BigDecimal("1"), this.getUomValue(UOM.Teaspoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("sugar", new BigDecimal("1"), this.getUomValue(UOM.Teaspoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("salt", new BigDecimal("1"), this.getUomValue(UOM.Teaspoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("clove garlic, finely chopped", new BigDecimal("1"), this.getUomValue(UOM.Each), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal("1"), this.getUomValue(UOM.Tablespoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal("3"), this.getUomValue(UOM.Tablespoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("olive oil", new BigDecimal("2"), this.getUomValue(UOM.Tablespoon), chickenTacos));
		chickenTacos.getIngredients().add(new Ingredient("boneless chicken thighs (1 1/4 pounds)", new BigDecimal("6"), this.getUomValue(UOM.Each), chickenTacos));
		
		return chickenTacos;
	}
	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
	
		
		recipes.add(this.getGuacamoleRecipe());
		recipes.add(this.getChickenTacos());
		return recipes;
	}
	
	private UnitOfMeasure getUomValue(UOM uom) {
		if(UomMap.isEmpty()) {
			this.populateUomMap();
		}
		return this.UomMap.get(uom.name());
	}
	private Map<String, Category> puopulateCategories() {
		Map<String, Category> categoryMap = new HashMap<String, Category>();
		List<Category> categoryList= (List<Category>) this.categoryRepo.findAll();
		Iterator<Category> i = categoryList.iterator();
		
		while(i.hasNext()) {
			Category category = i.next();
			this.categoryMap.put(category.getDescription(), category);
		}
		return categoryMap;
	}
    private  Category getCategory(Categories category) {
    	
    	if(this.categoryMap.isEmpty()) {
    		this.puopulateCategories();
    	}
    	return this.categoryMap.get(Categories.AMERICAN.toString());
    }
    @Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		List<Recipe> recipes = this.getRecipes();
		this.recipeRepo.saveAll(recipes);
		
	}
    public void saveRecipe() {
		List<Recipe> recipes = this.getRecipes();
		this.recipeRepo.saveAll(recipes);
	}
    
    public List<Recipe> getRecipesFromDB() {
    	List<Recipe> recipes = (List<Recipe>)this.recipeRepo.findAll();
    	return recipes;
    }
}
