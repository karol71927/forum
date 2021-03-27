package com.karol.forum.manager;

import com.karol.forum.dao.CategoryRepo;
import com.karol.forum.dao.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryManager {

    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Optional<Category> findById(Long id){
        return categoryRepo.findById(id);
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public List<Category> findByName(String name){
        return categoryRepo.findByName(name);
    }

    public Category save(Category category){
        return categoryRepo.save(category);
    }

    public void deleteById(Long id){
        categoryRepo.deleteById(id);
    }
}
