package com.ourhero.demo.controller;

import com.ourhero.demo.exception.ResourceNotFoundException;
import com.ourhero.demo.model.Admin;
import com.ourhero.demo.model.Request;
import com.ourhero.demo.model.User;
import com.ourhero.demo.model.Volunteer;
import com.ourhero.demo.repository.RequestRepository;
import com.ourhero.demo.repository.UserRepository;
import com.ourhero.demo.repository.VolunteerRepository;
import com.ourhero.demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private RequestService requestService;

    @GetMapping("")
    public List<Request> getAllRequest(){
        return requestRepository.findAll();
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Request> getUserById(@PathVariable long id){
//       Request request=requestRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not exist with id"+id));
//        return ResponseEntity.ok(request);
//    }

    @GetMapping("/{id}")
    public List<Request> getRequestsByUserId(@PathVariable long id){
        User user = userRepository.findByUserId(id);
        return (List<Request>) requestRepository.findByUser(user);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createRequest(@RequestBody Map<String,Object> payload){
        System.out.println("in controller");
        System.out.println(payload.toString());
        Request request = this.requestService.craeteRequest(payload);
//        User user = this.userRepository.findById((long)request.getUser().getUserId());
//
        return ResponseEntity.ok(request);
    }


    @PutMapping("/accept/{id}")
    public ResponseEntity<Request> acceptRequest(@PathVariable Long id,@RequestBody Map<String,Object> request)
    {
        Request  re=requestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("request not exist with id"+id));


       Volunteer  rq = this.volunteerRepository.findByVolunteerId(Long.parseLong((String) request.get("volunteer_id")));
        re.setVolunteer(rq);

       // Volunteer volunteer=this.volunteerRepository.findByVolunteerId(volunteerid);


       Request updateRequest=requestRepository.save(re);
        return ResponseEntity.ok(updateRequest);

    }


}
