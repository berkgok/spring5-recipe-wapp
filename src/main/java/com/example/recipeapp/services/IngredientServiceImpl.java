package com.example.recipeapp.services;

import com.example.recipeapp.commands.IngredientCommand;
import com.example.recipeapp.converters.IngredientToIngredientCommand;
import com.example.recipeapp.models.Recipe;
import com.example.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Recipe recipe = recipeOptional.get();

        // this line will get the ingredient set, filter the stream and match the ingredients with the given ingredientId then convert to ingredientCommand and get the first one
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredientSet().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        return ingredientCommandOptional.get();
    }
}
