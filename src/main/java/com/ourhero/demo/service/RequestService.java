package com.ourhero.demo.service;

import com.ourhero.demo.model.Request;
import com.ourhero.demo.model.User;
import com.ourhero.demo.repository.RequestRepository;
import com.ourhero.demo.repository.UserRepository;
import com.ourhero.demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Request craeteRequest(Map<String,Object> payload){
        System.out.println("In create Request method service");
        System.out.println(payload.toString());

        Request request = new Request();
        request.setRequestType((String) payload.get("requestType"));
        request.setRemarks((String) payload.get("remarks"));
        request.setVolunteer(null);
        User user = this.userRepository.findByUserId(Long.parseLong((String) payload.get("user_id")));
        request.setUser(user);
        Date date = new Date();
        request.setCreatedAt(new Timestamp(date.getTime()));

        return this.requestRepository.save(request);
    }

}
