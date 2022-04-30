package com.ourhero.demo.controller;


import com.ourhero.demo.exception.ResourceNotFoundException;
import com.ourhero.demo.model.User;
import com.ourhero.demo.model.Volunteer;
import com.ourhero.demo.repository.UserRepository;
import com.ourhero.demo.repository.VolunteerRepository;
import com.ourhero.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerService volunteerService;

    //get user details
    @GetMapping("")
    public List<Volunteer> getAllVolunteer(){
        return volunteerRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity< Volunteer> getVolunteerById(@PathVariable long id){
        Volunteer volunteer=volunteerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user not exist with id"+id));
        return ResponseEntity.ok(volunteer);
    }


    //create User
    //@PostMapping("")
   // public  Volunteer createVolunteer(@RequestBody  Volunteer volunteer){
    //    return volunteerRepository.save(volunteer);
    //}

    //update User
    @PutMapping("/enable/{id}")
    public ResponseEntity<Volunteer> enableVolunteer(@PathVariable Long id)
    {
        Volunteer  volunteer=volunteerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("volunteer not exist with id"+id));

//        volunteer.setFullName(volunteerDetails.getFullName());
//        volunteer.setAddress(volunteerDetails.getAddress());
//        volunteer.setEmail(volunteerDetails.getEmail());
        volunteer.setStatus("enable");
        Volunteer updateVolunteer=volunteerRepository.save(volunteer);
        return ResponseEntity.ok(updateVolunteer);

    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<Volunteer> disableVolunteer(@PathVariable Long id)
    {
        Volunteer  volunteer=volunteerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("volunteer not exist with id"+id));

//        volunteer.setFullName(volunteerDetails.getFullName());
//        volunteer.setAddress(volunteerDetails.getAddress());
//        volunteer.setEmail(volunteerDetails.getEmail());
        volunteer.setStatus("disable");
        Volunteer updateVolunteer=volunteerRepository.save(volunteer);
        return ResponseEntity.ok(updateVolunteer);

    }


    //Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>>deleteVolunteer(@PathVariable Long id){
        Volunteer volunteer =volunteerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Volunteer not exist with id:"+id));

        volunteerRepository.delete(volunteer);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


    //Register for volunteer
    @PostMapping("/register")
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer){

        return volunteerService.register(volunteer);
    }

    //login volunteer
    @PostMapping("/login")
    public Volunteer login(@RequestBody Volunteer volunteer){

        return volunteerService.login(volunteer);
    }


}
