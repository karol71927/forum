package com.karol.forum.dao;

import com.karol.forum.dao.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<Post, Long> {
    List<Post> findByThemaLike(String thema);
}
