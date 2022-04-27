package com.ourhero.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long adminId;

    @Column(nullable = false,unique = true)
    private  String userName;

    @Column(nullable = false)
    private String passWord;
}
