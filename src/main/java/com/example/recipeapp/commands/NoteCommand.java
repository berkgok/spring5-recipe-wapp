package com.example.recipeapp.commands;

public class NoteCommand {

    private Long id;
    private RecipeCommand recipe;
    private String recipeNote;

    public NoteCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeCommand getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeCommand recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNote() {
        return recipeNote;
    }

    public void setRecipeNote(String recipeNote) {
        this.recipeNote = recipeNote;
    }
}
