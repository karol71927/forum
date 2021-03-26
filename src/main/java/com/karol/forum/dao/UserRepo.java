package com.karol.forum.dao;

import com.karol.forum.dao.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
