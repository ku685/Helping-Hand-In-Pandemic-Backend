package com.ourhero.demo.service;

import com.ourhero.demo.model.Admin;
import com.ourhero.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin login(Admin admin) {
        Admin existingAdmin=adminRepository.findByUserName(admin.getUserName());

        if(existingAdmin.getUserName().equals(admin.getUserName()) && existingAdmin.getPassWord().equals(admin.getPassWord()))
        {
            existingAdmin.setPassWord("");
            return existingAdmin;
        }

        return null;
    }

}
