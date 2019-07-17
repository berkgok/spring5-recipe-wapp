package com.example.recipeapp.services;

import com.example.recipeapp.commands.IngredientCommand;
import com.example.recipeapp.converters.IngredientToIngredientCommand;
import com.example.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipeapp.models.Ingredient;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientService ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand((new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIdHappyPath() {

        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient1.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient1.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndId(1L, 3L);

        // then
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());

    }

}