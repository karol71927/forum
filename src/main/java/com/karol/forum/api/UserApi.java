package com.karol.forum.api;

import com.karol.forum.dao.entity.User;
import com.karol.forum.dto.UserDto;
import com.karol.forum.manager.UserManager;
import com.karol.forum.middleware.Hash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private UserManager userManager;
    private ModelMapper modelMapper;

    @Autowired
    public UserApi(UserManager userManager, ModelMapper modelMapper) {
        this.userManager = userManager;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public UserDto register(@RequestBody UserDto userDto){
        List<User> list = userManager.findByLogin(userDto.getLogin());
        if(!list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with this login already exists");
        }
        list.clear();
        list = userManager.findByEmail(userDto.getEmail());
        if(!list.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with this email already exists");
        }
        String password = Hash.hash(userDto.getPassword());
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(password);
        user.setType("user");
        User userSaved = userManager.save(user);
        UserDto userDto1 = modelMapper.map(userSaved,UserDto.class);
        return userDto1;
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto){
        Optional<User> optional = userManager.findById(id);
        User user = optional.get();
        userDto.setLogin(user.getLogin());
        if(userDto.getPassword() != null){
            String password = Hash.hash(userDto.getPassword());
            user.setPassword(password);
        }
        if(userDto.getEmail() != null){
            List<User> list = userManager.findByEmail(userDto.getEmail());
            if(!list.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user with this email already exists");
            } else {
                user.setEmail(userDto.getEmail());
            }
        }
        User saved = userManager.save(user);
        UserDto userDto1 = modelMapper.map(saved, UserDto.class);
        return userDto1;
    }
}
