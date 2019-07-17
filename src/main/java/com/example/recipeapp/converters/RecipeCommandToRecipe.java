package com.example.recipeapp.converters;

import com.example.recipeapp.commands.RecipeCommand;
import com.example.recipeapp.models.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NoteCommandToNote noteConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter,
                                 NoteCommandToNote notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());

        if(source.getNote() != null) {
            recipe.setNote(noteConverter.convert(source.getNote()));
        }

        if (source.getCategorySet() != null && source.getCategorySet().size() > 0){
            source.getCategorySet()
                    .forEach( category -> recipe.getCategorySet().add(categoryConverter.convert(category)));
        }

        if (source.getIngredientSet() != null && source.getIngredientSet().size() > 0){
            source.getIngredientSet()
                    .forEach(ingredient -> recipe.getIngredientSet().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
