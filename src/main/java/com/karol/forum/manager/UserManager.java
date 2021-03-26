package com.karol.forum.manager;

import com.karol.forum.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManager {
    private UserManager userManager;

    @Autowired
    public UserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public Optional<User> findById(Long id){
        return userManager.findById(id);
    }

    public Iterable<User> findAll(){
        return userManager.findAll();
    }

    public User save(User user){
        return userManager.save(user);
    }

    public void deleteById(Long id){
        userManager.deleteById(id);
    }
}
