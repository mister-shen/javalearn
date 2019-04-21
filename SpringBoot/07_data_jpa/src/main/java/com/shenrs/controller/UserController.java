package com.shenrs.controller;

import com.shenrs.entity.User;
import com.shenrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author shenrs
 * @Description
 * @create 2019-03-29 17:06
 **/
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        Example<User> example = Example.of(user);
        Optional<User> optional = userRepository.findOne(example);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        userRepository.save(user);
        return user;
    }
}
