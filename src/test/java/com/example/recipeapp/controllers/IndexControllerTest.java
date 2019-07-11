package com.example.recipeapp.controllers;

import com.example.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {

        assertEquals(indexController.getIndexPage(model), "index");
        verify(recipeService,times(1)).getRecipes();
        verify(model,times(1)).addAttribute(anyString(), any()); // since we are verifying the count of the method usage, we can give any string, any variable to the method
    }

}