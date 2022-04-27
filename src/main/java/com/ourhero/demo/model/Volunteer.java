package com.ourhero.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long volunteerId;

    @Column(name="user_name",unique = true,updatable = false)
    private  String  userName;

    @Column(name="pass_word")
    private String passWord;

    @Column(name = "full_name")
    private String fullName;
    @Column(name="age")
    private  Integer age;
    @Column(name="gender")
    private  String gender;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="mobile",unique = true,nullable = false)
    private long mobile;
    @Column(name="address")
    private String address;
    @Column(name="city",nullable = false)
    private String city;
    @Column(name="state",nullable = false)
    private String state;
    @Column(name="pincode",nullable = false)
    private Integer pincode;



    private  String token;

//    @OneToMany(mappedBy = "user")
//    private Set<Request> request;


}
