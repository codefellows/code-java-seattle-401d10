package com.codefellows.dinosaurs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DinosaurController {
    @GetMapping("/")
    public String showDinoHome(Principal p){ // Principal == the user
        System.out.println("p" + p);
        if(p != null){
            System.out.println("p.getName() = " + p.getName());
        }

        return "index";
    }

    @GetMapping("/dinosaurs")
    public String showDinos(Principal p, Model m){ // principal is like Model m. it represents the logged in user
        System.out.println("p.getName() = " + p.getName());

        m.addAttribute("user", p.getName());
        return "dinosaurs";
    }
}
