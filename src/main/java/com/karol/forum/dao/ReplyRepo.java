package com.karol.forum.dao;

import com.karol.forum.dao.entity.Reply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepo extends CrudRepository<Reply, Long> {
    List<Reply> findByPostID(Long Id);
}
