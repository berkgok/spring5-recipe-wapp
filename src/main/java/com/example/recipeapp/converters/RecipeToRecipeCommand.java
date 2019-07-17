package com.example.recipeapp.converters;

import com.example.recipeapp.commands.RecipeCommand;
import com.example.recipeapp.models.Category;
import com.example.recipeapp.models.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NoteToNoteCommand noteConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter,
                                 NoteToNoteCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setImage(source.getImage());
        command.setNote(noteConverter.convert(source.getNote()));

        if (source.getCategorySet() != null && source.getCategorySet().size() > 0) {
            source.getCategorySet()
                    .forEach((Category category) -> command.getCategorySet().add(categoryConverter.convert(category)));
        }

        if (source.getIngredientSet() != null && source.getIngredientSet().size() > 0) {
            source.getIngredientSet()
                    .forEach(ingredient -> command.getIngredientSet().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
