package com.codefellows.dinosaurs;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class AuthenticationController {
    @Autowired DinosaurRepository dinosaurRepository; // the repository is like pg client

    @PostMapping("/signup")
    public RedirectView signup(String username, String password){
        System.out.println("username = " + username);
        System.out.println("password = " + password);


        String hashedPAssword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println("hashedPAssword = " + hashedPAssword);

        Dinosaur dino = new Dinosaur();
        dino.username = username;
        dino.password = hashedPAssword;

        dinosaurRepository.save(dino);

        return new RedirectView("/");
    }

    @PostMapping("/login") // Why is this a post, sending data securely, Creating a new session
    public RedirectView login(String username, String password){
//        we will pass cookies to the front end
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        Dinosaur dinoFromDb = dinosaurRepository.findByUsername(username);
        if(dinoFromDb == null){
            return new RedirectView("/extinction");
        }

        if(passwordCheck(dinoFromDb, password)){
            return new RedirectView("/?loggedIn=true");
        } else {
            return new RedirectView("/password-incorrect");
        }
    }

    public boolean passwordCheck(Dinosaur dino, String password){
        return BCrypt.checkpw(password, dino.password);
//        if(password.equals(dino.password)){
//            return true;
//        } else {
//            return false;
//        }
    }

    @GetMapping("/extinction")
    public String showUsernameDoesNotExist(){
        return "extinction.html";
    }

    @GetMapping("/password-incorrect")
    public String showPasswordIncorrect(){
        return "password-incorrect.html";
    }
}
