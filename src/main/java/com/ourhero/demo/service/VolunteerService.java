package com.ourhero.demo.service;

import com.ourhero.demo.model.User;
import com.ourhero.demo.model.Volunteer;
import com.ourhero.demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    private  static  final SecureRandom secureRandom=new SecureRandom();

    private static  final Base64.Encoder base64encoder=Base64.getUrlEncoder();

    public Volunteer register(Volunteer volunteer)
    {
        if(checkUserExist(volunteer)==true)
            return null;

        volunteer.setToken(generatetoken());
        return   volunteerRepository.save(volunteer);

    }

    private String generatetoken() {
        byte[] token =new byte[24];
        secureRandom.nextBytes(token);
        return base64encoder.encodeToString(token);
    }

    private boolean checkUserExist(Volunteer volunteer) {
        Volunteer existingVolunteer=volunteerRepository.findByUserName(volunteer.getUserName());
        if(existingVolunteer==null)
            return false;
        else
            return true;
    }

    public Volunteer login(Volunteer volunteer) {
        Volunteer existingVolunteer=volunteerRepository.findByUserName(volunteer.getUserName());

        if(existingVolunteer.getUserName().equals(volunteer.getUserName()) && existingVolunteer.getPassWord().equals(volunteer.getPassWord()))
        {
            existingVolunteer.setPassWord("");
            return existingVolunteer;
        }

        return null;
    }
}
