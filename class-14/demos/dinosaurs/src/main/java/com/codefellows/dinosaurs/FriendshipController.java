package com.codefellows.dinosaurs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FriendshipController {
    @GetMapping("/")
    public String showHomePage(boolean loggedIn, Model m){
        if(loggedIn){
            m.addAttribute("loggedIn", true);
        }
        return "friends.html";
    }

    @GetMapping("/admin")
    public String showAdminPage(boolean loggedIn){
        if(loggedIn){
            return "admin.html";
        } else {
           return "extinction.html";
        }
    }
}
