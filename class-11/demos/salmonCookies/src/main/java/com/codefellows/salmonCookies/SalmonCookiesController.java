package com.codefellows.salmonCookies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class SalmonCookiesController {
    static ArrayList<CookieStore> cookieStores = new ArrayList<>();
    static {
        cookieStores.add(
                new CookieStore("Hilo Aquatic Pet Food", 10, 10000000)
        );
        cookieStores.add(
                new CookieStore("Kenai Krazy Krabs (and Fish food)", 1, 10)
        );
    }

    @GetMapping("/")
    public String showSalesPage(Model modelPotato){

        CookieStore amqui = new CookieStore("amqui cookies", 10000, 100000);
        modelPotato.addAttribute("store", amqui);


        modelPotato.addAttribute("storeArray", cookieStores);


        return "salmon-cookies.html";
    }

    @PostMapping("/cookie-store")
    public RedirectView addCookieStore(
            String namePotato,
            int minSales,
            int maxSales
    ){ // in a PostMapping, the body parameters from formdata are the assumed parameters
        CookieStore cookieStore = new CookieStore(namePotato, minSales, maxSales);
        cookieStores.add(cookieStore);
        return new RedirectView("/"); // sends the client to a different route
    }
}
