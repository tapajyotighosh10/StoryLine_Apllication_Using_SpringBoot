package com.blogging.app.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name",nullable = false,length = 50)
    private String name;
    @Column(name = "email",nullable = false,length = 50)
    private String email;
    @Column(name = "password",nullable = false,length = 16)
    private String password;
    @Column(name = "about",nullable = true,length = 200)
    private String about;


}
