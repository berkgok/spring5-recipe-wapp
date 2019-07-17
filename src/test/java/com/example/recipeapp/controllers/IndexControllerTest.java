package com.example.recipeapp.controllers;

import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testMockMVC() throws Exception {    // with this, we can test controllers without bringing spring context. ez pz
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        // given
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(new Recipe());
        recipeSet.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipeSet); // whenever recipeService.getRecipes() method has been called, return recipeSet

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class); // this will return the ArgumentCaptor object with given Class type (which is Set<Recipe> in this example)

        // when
        String viewName = indexController.getIndexPage(model);

        // then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture()); // when we run the getIndexPage(model) method, model.AddAttribute will have 2 arguments, in this line we are capturing the second argument which is recipeService.getRecipes(), since we modified the return of this method, we'r gonna get recipeSet as the value of this argumetnCaptor
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}