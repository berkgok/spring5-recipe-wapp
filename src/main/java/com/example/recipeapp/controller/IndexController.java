package com.example.recipeapp.controller;

import com.example.recipeapp.models.Category;
import com.example.recipeapp.models.UnitOfMeasure;
import com.example.recipeapp.repositories.CategoryRepository;
import com.example.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping("")
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Category category = categoryOptional.get();

        Optional<UnitOfMeasure>  unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        UnitOfMeasure unitOfMeasure = unitOfMeasureOptional.get();

        System.out.println("Category ID is: " + category.getId());
        System.out.println("Unit of Measure ID is " + unitOfMeasure.getId());
        return "index";
    }
}
