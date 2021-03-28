package com.karol.forum.dao;

import com.karol.forum.dao.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    List<Post> findByTopicLike(String topic);
}
