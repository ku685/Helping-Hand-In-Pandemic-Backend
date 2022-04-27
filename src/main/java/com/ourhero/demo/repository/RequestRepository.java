package com.ourhero.demo.repository;

import com.ourhero.demo.model.Admin;
import com.ourhero.demo.model.Request;
import com.ourhero.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findByUser(User user);

}
