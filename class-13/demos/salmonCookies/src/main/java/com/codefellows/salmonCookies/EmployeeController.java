package com.codefellows.salmonCookies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;

@Controller
public class EmployeeController {
    @Autowired
    CookieStoreRepository cookieStoreRepository;
    @Autowired
    EmployeeRepository employeeRepository; // the repository lets us interact with sql

    @PostMapping("/employee")
    public RedirectView addEmployee(String name, int payPerHour, long id){
//        Need the name, price and CookieStore
//        1. get the store
        CookieStore store = cookieStoreRepository.getOne(id);
        Employee employee = new Employee(name, payPerHour, store);
        employeeRepository.save(employee);
        return new RedirectView("/");
    }
}
