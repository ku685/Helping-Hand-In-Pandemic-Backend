package com.ourhero.demo.repository;

import com.ourhero.demo.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer,Long> {
    Volunteer findByUserName(String userName);
    Volunteer findByVolunteerId(Long volunteerId);
}
