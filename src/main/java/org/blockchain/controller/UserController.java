package org.blockchain.controller;

import org.blockchain.entity.User;
import org.blockchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create new user in the system
     * @param user containing use data
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody(required = true) User user){
        userRepository.save(user);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    /**
     * list all users in database
     */
    @RequestMapping("/list")
    public ResponseEntity<?> list(){
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
