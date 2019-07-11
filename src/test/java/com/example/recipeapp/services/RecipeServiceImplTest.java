package com.example.recipeapp.services;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // this means give me a mock recipe repository

        recipeService = new RecipeServiceImpl(recipeRepository);
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

        assertEquals(recipeSet.size(), 1);

        // we can also verify the interactions, in this line we checked that findAll() method has invoked 1 times
        verify(recipeRepository, times(1)).findAll();
    }
}