package com.example.recipeapp.bootstrap;

import com.example.recipeapp.models.*;
import com.example.recipeapp.repositories.CategoryRepository;
import com.example.recipeapp.repositories.RecipeRepository;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipeList = new ArrayList<>(2);

        // get data from database

        // for category
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        Category americanCategory = americanCategoryOptional.get();

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        Category italianCategory = italianCategoryOptional.get();

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        Category mexicanCategory = mexicanCategoryOptional.get();

        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
        Category fastFoodCategory = fastFoodCategoryOptional.get();

        Optional<Category> veganCategoryOptional = categoryRepository.findByDescription("Vegan");
        Category veganCategory = veganCategoryOptional.get();

        //for unit of measure
        Optional<UnitOfMeasure> teaSpoonUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        UnitOfMeasure teaSpoonUnitOfMeasure = teaSpoonUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> tableSpoonUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Tablespoon");
        UnitOfMeasure tableSpoonUnitOfMeasure = tableSpoonUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> cupUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Cup");
        UnitOfMeasure cupUnitOfMeasure = cupUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> pinchUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Pinch");
        UnitOfMeasure pinchUnitOfMeasure = pinchUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> ounceUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Ounce");
        UnitOfMeasure ounceUnitOfMeasure = ounceUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> dashUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Dash");
        UnitOfMeasure dashUnitOfMeasure = dashUnitOfMeasureOptional.get();

        Optional<UnitOfMeasure> eachUnitOfMeasureOptional = unitOfMeasureRepository.findByUom("Each");
        UnitOfMeasure eachUnitOfMeasure = eachUnitOfMeasureOptional.get();

        // Guacamalo Recipe
        Recipe guacamole = new Recipe();
        guacamole.setDescription("How to Make Perfect Guacamole");
        guacamole.setPrepTime(10); // minutes
        guacamole.setCookTime(0);
        guacamole.setServings(2); // 2 - 4
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        guacamole.setDifficulty(Difficulty.EASY);

        // adding ingredients for guacamole
        guacamole.getIngredientSet().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tableSpoonUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUnitOfMeasure, guacamole));
        guacamole.getIngredientSet().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUnitOfMeasure, guacamole));

        guacamole.setImage(null);

        // creating note for guacamole
        Note guacamoleNote = new Note();
        guacamoleNote.setRecipe(guacamole);
        guacamoleNote.setRecipeNote("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");
        // setting note to guacamole
        guacamole.setNote(guacamoleNote);

        guacamole.getCategorySet().add(mexicanCategory);
        guacamole.getCategorySet().add(americanCategory);
        guacamole.getCategorySet().add(veganCategory);

        // add the recipe to the recipe list
        recipeList.add(guacamole);

        return recipeList;
    }

}
