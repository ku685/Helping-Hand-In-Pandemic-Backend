package com.ourhero.demo.service;

import com.ourhero.demo.model.User;
import com.ourhero.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private  static  final SecureRandom secureRandom=new SecureRandom();

    private static  final Base64.Encoder base64encoder=Base64.getUrlEncoder();

    public User register(User user)
    {
        if(checkUserExist(user)==true)
            return null;

        user.setToken(generatetoken());
      return   userRepository.save(user);

    }

    private String generatetoken() {
        byte[] token =new byte[24];
        secureRandom.nextBytes(token);
        return base64encoder.encodeToString(token);
    }

    private boolean checkUserExist(User user) {
        User existingUser=userRepository.findByUserName(user.getUserName());
        if(existingUser==null)
            return false;
        else
            return true;
    }

    public User login(User user) {
        User existingUser=userRepository.findByUserName(user.getUserName());

        if(existingUser.getUserName().equals(user.getUserName()) && existingUser.getPassWord().equals(user.getPassWord()))
        {
            existingUser.setPassWord("");
            System.out.println(existingUser.toString());
            return existingUser;
        }

        return null;
    }
}
