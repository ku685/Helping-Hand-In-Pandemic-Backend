package com.ourhero.demo.controller;
import com.ourhero.demo.model.User;
import com.ourhero.demo.exception.ResourceNotFoundException;
import com.ourhero.demo.repository.UserRepository;
import com.ourhero.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    //get user details
    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not exist with id"+id));
        return ResponseEntity.ok(user);
    }


    //register User
    @PostMapping("/register")
    public User createUser(@RequestBody User user){

        return userService.register(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user){

        return userService.login(user);
    }

    //update User
    @PutMapping("/enable/{id}")
    public ResponseEntity<User> enableUser(@PathVariable Long id)
    {
        User  user=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not exist with id"+id));

//        user.setFullName(userDetails.getFullName());
//        user.setAddress(userDetails.getAddress());
//        user.setEmail(userDetails.getEmail());
        user.setStatus("enable");
        User updateUser=userRepository.save(user);
        return ResponseEntity.ok(updateUser);

    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<User> disableUser(@PathVariable Long id)
    {
        User  user=userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not exist with id"+id));

//        user.setFullName(userDetails.getFullName());
//        user.setAddress(userDetails.getAddress());
//        user.setEmail(userDetails.getEmail());
        System.out.println(user);
        user.setStatus("disable");

        User updateUser=userRepository.save(user);
        System.out.println(updateUser);
        return ResponseEntity.ok(updateUser);

    }


    //Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>>deleteUser(@PathVariable Long id){
        User user =userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id:"+id));

        userRepository.delete(user);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

   // @PutMapping("/enable")


}
