package com.codefellows.salmonCookies;

//This never needs to be connected to SalmonCookiesApplication via import
//An ANNOTATION will connect them
//@Controller will tell spring to use this class as a controller file (read it as routes)

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeachingController {
//    Routes themselves are ANNOTATED to with 'METHODMapping' annotations
    @GetMapping("/hello-world")
    public String showHelloWorldPage(){
//        The String returned by the method is the fileName that will be shown from 'templates'
        return "hi-there-world.html";
    }

    @GetMapping("/bff")
    public String renderBffPage(Model m){ // Model is from MVC, it shapes data for the template
// Returnign a string tells Spring : the string will be the fileName
//        Pass the data to Thymeleaf (templating engine)
        m.addAttribute("name", "Joel");
//        m.addAttribute("friendshipDuration", 2); // I left this off, and thymeleaf did not break
//        If a value is null or empty, thymeleaf just ignores it
        m.addAttribute("occupation", "Tech Stuff, fixing coputers, building servers");
        return "best-friend.html";
    }

//    Path Variables
    @GetMapping("/bff2/{name}")
    public String renderBffPage2(
            Model m,
            @PathVariable String name, // path variables need an annotation
            String occupation // query parameters are implied
    ){
        System.out.println("name = " + name);
        System.out.println("occupation = " + occupation);
        m.addAttribute("name", name);
        m.addAttribute("friendshipDuration", 2);
        m.addAttribute("occupation", occupation);
        return "best-friend2.html";
    }
}
