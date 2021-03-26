package com.karol.forum.dao;

import com.karol.forum.dao.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}
