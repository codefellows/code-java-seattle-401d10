package com.codefellows.dinosaurs.controllers;

import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUser;
import com.codefellows.dinosaurs.models.dinosaurUser.DinosaurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class DinosaurUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DinosaurUserRepository dinoUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/dinosaurUser")
    public RedirectView createUser(String username, String password, HttpServletRequest request){
        String passwordEncoded = passwordEncoder.encode(password);
        System.out.println("password = " + passwordEncoded);
        DinosaurUser dinoUser = new DinosaurUser();
        dinoUser.setPassword(passwordEncoded);
        dinoUser.setUsername(username);

        try {
            dinoUserRepository.save(dinoUser);
        } catch(Exception e){
            return new RedirectView("/?username=duplicate");
//            TODO: tell the user what went wrong
        }

//        This is how login creates and sets tokens, we should be able to hijack it for signup
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
//        TODO: create users
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }

    @GetMapping("/dinosaurUser/{id}")
    public String showSingleUser(@PathVariable long id, Model m, Principal p){
        DinosaurUser dinosaurUser = dinoUserRepository.findById(id).get();
        m.addAttribute("dinoUser", dinosaurUser);
        if(p != null){
            DinosaurUser visitor = dinoUserRepository.findByUsername(p.getName());
            if(!visitor.getUsername().equals(dinosaurUser.getUsername())){
                m.addAttribute("visitor", visitor);
            }

        }else {
            DinosaurUser visitor = new DinosaurUser();
            visitor.setUsername("Guest");
            m.addAttribute("visitor", visitor);
        }

        return "dinosaurUser.html";
    }

    @PutMapping("/dinosaurUser/{id}")
    public RedirectView updateBio(@PathVariable long id, String bio){
        DinosaurUser dinoUser = dinoUserRepository.findById(id).get();
        dinoUser.bio = bio;
        dinoUserRepository.save(dinoUser);
        return new RedirectView("/dinosaurUser/" + id);
    }
}
