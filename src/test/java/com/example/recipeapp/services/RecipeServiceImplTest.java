package com.example.recipeapp.services;

import com.example.recipeapp.converters.RecipeCommandToRecipe;
import com.example.recipeapp.converters.RecipeToRecipeCommand;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() {
        // we created dummy recipes set and a recipe, and added the recipe to recipe set
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet<>();
        recipesData.add(recipe);

        // when the getRecipes() method is called, return recipesData
        when(recipeService.getRecipes()).thenReturn(recipesData);

        // here is the calling of getRecipes() method so we will get recipesData as a return
        Set<Recipe> recipeSet = recipeService.getRecipes();

        assertEquals(1, recipeSet.size());

        // we can also verify the interactions, in this line we checked that findAll() method has invoked 1 times
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void deleteById() throws Exception {

        // given
        Long idToDelete = Long.valueOf(2L);

        // when
        recipeService.deleteById(idToDelete);

        // then
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }
}