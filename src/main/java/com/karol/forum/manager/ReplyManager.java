package com.karol.forum.manager;

import com.karol.forum.dao.ReplyRepo;
import com.karol.forum.dao.entity.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReplyManager {
    private ReplyRepo replyRepo;

    @Autowired
    public ReplyManager(ReplyRepo replyRepo) {
        this.replyRepo = replyRepo;
    }

    public Optional<Reply> findById(Long id){
        return replyRepo.findById(id);
    }

    public Iterable<Reply> findAll(){
        return replyRepo.findAll();
    }

    public Reply save(Reply reply){
        return replyRepo.save(reply);
    }

    public void deleteById(Long id){
        replyRepo.deleteById(id);
    }

    public List<Reply> findByPostId(Long id){
        return replyRepo.findByPostID(id);
    }
}
