package com.ourhero.demo.repository;

import com.ourhero.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);

    User findByUserId(Long userID);
}
