package com.example.recipeapp.commands;

import com.example.recipeapp.models.Difficulty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class RecipeCommand {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @Range(min = 1, max = 999)
    private Integer prepTime;

    @NotNull(message = "enter 0 if there is no need to cook")
    @Max(999)
    private Integer cookTime;

    @NotNull
    @Range(min = 1)
    private Integer servings;

    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;

    private Difficulty difficulty;
    private Set<IngredientCommand> ingredientSet = new HashSet<>();
    private Byte[] image;
    private NoteCommand note;
    private Set<CategoryCommand> categorySet = new HashSet<>();

    public RecipeCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<IngredientCommand> getIngredientSet() {
        return ingredientSet;
    }

    public void setIngredientSet(Set<IngredientCommand> ingredientSet) {
        this.ingredientSet = ingredientSet;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public NoteCommand getNote() {
        return note;
    }

    public void setNote(NoteCommand note) {
        this.note = note;
    }

    public Set<CategoryCommand> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<CategoryCommand> categorySet) {
        this.categorySet = categorySet;
    }
}
