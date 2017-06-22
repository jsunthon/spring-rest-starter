package com.tyco.controllers;

import com.tyco.models.User;
import com.tyco.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jamessunthonlap on 6/22/17.
 */
@RestController
@RequestMapping(path = "users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/{name}/{email}", method = RequestMethod.POST)
    public User addUser(@PathVariable() String name
            , @PathVariable() String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String deleteUser(@PathVariable() long id) {
        userRepository.deleteById(id);
        return "Deleted";
    }
}
