package com.codefellows.dinosaurs.controllers;

import com.codefellows.dinosaurs.models.dinosaur.Dinosaur;
import com.codefellows.dinosaurs.models.dinosaur.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class DinosaurController {

    @Autowired
    DinosaurRepository dinosaurRepository;

    @GetMapping("/")
    public String showDinoHome(Principal p, Model m){ // Principal == the user
        System.out.println("p" + p);
        if(p != null){
            System.out.println("p.getName() = " + p.getName());
            m.addAttribute("user", p.getName());
        }

        return "index";
    }

    @GetMapping("/dinosaurs")
    public String showDinos(Principal p, Model m){ // principal is like Model m. it represents the logged in user
        System.out.println("p.getName() = " + p.getName());

        m.addAttribute("user", p.getName());

        List<Dinosaur> dinosaurs = dinosaurRepository.findAll();

        m.addAttribute("dinos", dinosaurs);

        boolean partyTime = true;
        for(Dinosaur dino : dinosaurs){
            if(dino.getDinosaursIHaveReceivedCakeFrom().size() < dinosaurs.size() -1 ){
                partyTime = false;
            }
        }
        m.addAttribute("partyTime", partyTime);
        return "dinosaurs";
    }

    @GetMapping("/*")
    public String catchAll(){
        return"dinosaurs.html";
    }

    @PostMapping("/dinosaur")
    public RedirectView showDinos(boolean carnivore, String name, String color, int scales){
        Dinosaur dino = new Dinosaur();
        dino.setCarnivore(carnivore);
        dino.setScales(scales);
        dino.setName(name);
        dino.setColor(color);

        dinosaurRepository.save(dino);
        return new RedirectView("/dinosaurs");
    }

    @PostMapping("/cakeGiver")
    public RedirectView giveCake(long giverId, long receiverId){
        Dinosaur giver = dinosaurRepository.findById(giverId).get();
        Dinosaur receiver = dinosaurRepository.findById(receiverId).get();

        giver.getDinosaursIHaveGivenACakeTo().add(receiver);
//        receiver.getDinosaursIHaveReceivedCakeFrom().add(giver);

        dinosaurRepository.save(giver);

        return new RedirectView("/dinosaurs");
    }
}
