package com.karol.forum.manager;

import com.karol.forum.dao.PostRepo;
import com.karol.forum.dao.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Post> findByThemaLike(String thema){
        return postRepo.findByThemaLike(thema);
    }

    public Post save(Post post){
        return postRepo.save(post);
    }

    public void deleteById(Long id){
        postRepo.deleteById(id);
    }
}
