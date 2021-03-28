package com.karol.forum.manager;

import com.karol.forum.dao.PostRepo;
import com.karol.forum.dao.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostManager {
    private PostRepo postRepo;

    @Autowired
    public PostManager(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Optional<Post> findById(Long id){
        return postRepo.findById(id);
    }

    public Iterable<Post> findAll(){
        return postRepo.findAll();
    }

    public List<Post> findByTopicLike(String topic){
        return postRepo.findByTopicLike(topic);
    }

    public Post save(Post post){
        Date date = new Date(System.currentTimeMillis());
        post.setDate(date);
        return postRepo.save(post);
    }

    public void deleteById(Long id){
        postRepo.deleteById(id);
    }
}
