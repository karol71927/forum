package com.karol.forum.dao;

import com.karol.forum.dao.entity.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}
