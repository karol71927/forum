package com.karol.forum.api;

import com.karol.forum.dao.entity.Category;
import com.karol.forum.dto.CategoryDto;
import com.karol.forum.dto.PostDto;
import com.karol.forum.manager.CategoryManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    private CategoryManager categoryManager;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryApi(CategoryManager categoryManager, ModelMapper modelMapper) {
        this.categoryManager = categoryManager;
        this.modelMapper = modelMapper;
    }

    private CategoryDto convertToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category convertToEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;

    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id){
        Optional<Category> category = categoryManager.findById(id);
        return convertToDto(category.get());
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        Iterable<Category> categories = categoryManager.findAll();
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(categoryList::add);
        return categoryList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto){
        Category category = convertToEntity(categoryDto);
        return convertToDto(categoryManager.save(category));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryManager.deleteById(id);
    }


}
