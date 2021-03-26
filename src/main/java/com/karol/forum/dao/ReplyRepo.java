package com.karol.forum.dao;

import com.karol.forum.dao.entity.Reply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepo extends CrudRepository<Reply, Long> {
}
