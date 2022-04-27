package com.ourhero.demo.controller;

import com.ourhero.demo.exception.ResourceNotFoundException;
import com.ourhero.demo.model.Admin;
import com.ourhero.demo.model.User;
import com.ourhero.demo.model.Volunteer;
import com.ourhero.demo.repository.AdminRepository;
import com.ourhero.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getUserById(@PathVariable long id){
        Admin admin=adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("admin not exist with id"+id));
        return ResponseEntity.ok(admin);
    }

    @PostMapping("")
    public Admin createAdmin(@RequestBody  Admin admin){
        return adminRepository.save(admin);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody  Admin adminDetails)
    {
       Admin  admin=adminRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("adminnot exist with id"+id));


        admin.setPassWord(adminDetails.getPassWord());

        Admin updateAdmin=adminRepository.save(admin);
        return ResponseEntity.ok(updateAdmin);

    }
    //Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>>deleteVolunteer(@PathVariable Long id){
       Admin admin =adminRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("admin not exist with id:"+id));

        adminRepository.delete(admin);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);

    }


    //LOGIN ADMIN
    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){

        return adminService.login(admin);
    }

}
