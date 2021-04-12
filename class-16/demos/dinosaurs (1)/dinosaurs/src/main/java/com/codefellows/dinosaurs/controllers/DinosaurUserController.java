package com.codefellows.dinosaurs.controllers;

import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUser;
import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;

@Controller
public class DinosaurUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DinosaurUserRepository dinoUserRepository;

    @PostMapping("/dinosaurUser")
    public RedirectView createUser(String username, String password){
        password = passwordEncoder.encode(password);
        System.out.println("password = " + password);
        DinosaurUser dinoUser = new DinosaurUser();
        dinoUser.setPassword(password);
        dinoUser.setUsername(username);

        dinoUserRepository.save(dinoUser);
        return new RedirectView("/");
//        TODO: create users
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }
}
