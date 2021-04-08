package com.codefellows.dinosaurs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dinosaur {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String username;
    String password;
}
