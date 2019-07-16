package com.example.recipeapp.converters;

import com.example.recipeapp.commands.NoteCommand;
import com.example.recipeapp.models.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTE = "Note";
    NoteToNoteCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    public void convert() throws Exception {
        //given
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setRecipeNote(RECIPE_NOTE);

        //when
        NoteCommand noteCommand = converter.convert(note);

        //then
        assertEquals(ID_VALUE, noteCommand.getId());
        assertEquals(RECIPE_NOTE, noteCommand.getRecipeNote());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Note()));
    }
}