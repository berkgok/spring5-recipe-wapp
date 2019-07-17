package com.example.recipeapp.services;

import com.example.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);
    public IngredientCommand saveIngredientCommand(IngredientCommand command);
}
