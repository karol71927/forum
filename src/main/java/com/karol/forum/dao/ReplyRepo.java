package com.karol.forum.dao;

import com.karol.forum.dao.entity.Reply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepo extends CrudRepository<Reply, Long> {
}
