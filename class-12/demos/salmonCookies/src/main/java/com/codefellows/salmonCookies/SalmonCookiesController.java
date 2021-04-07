package com.codefellows.salmonCookies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

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

//    ==============JPA STUFF
    @Autowired
    public CookieStoreRepository cookieStoreRepository;
    // Autowired will instantiate the cookieStoreRepository
//    This is an interface, an instance of JPARepositoryInteraction that implements CookieStoreRepository
    // ======================



    @GetMapping("/")
    public String showSalesPage(Model modelPotato){

//    We can retrieve from the database now
        List<CookieStore> cookieStores = cookieStoreRepository.findAll();
        modelPotato.addAttribute("storeArray", cookieStores);
        System.out.println("====================================");
        System.out.println("id: " + cookieStores.get(0).id);

        return "salmon-cookies.html";
    }

    @PostMapping("/cookie-store")
    public RedirectView addCookieStore(
            String namePotato,
            int minSales,
            int maxSales
    ){ // in a PostMapping, the body parameters from formdata are the assumed parameters
        CookieStore cookieStore = new CookieStore(namePotato, minSales, maxSales);
//        cookieStores.add(cookieStore);
        cookieStoreRepository.save(cookieStore);
        return new RedirectView("/"); // sends the client to a different route
    }

    @DeleteMapping("/cookie-store/{id}")
    public RedirectView deleteCookieStore(@PathVariable long id){
        cookieStoreRepository.deleteById(id);
        return new RedirectView("/");
    }
}
