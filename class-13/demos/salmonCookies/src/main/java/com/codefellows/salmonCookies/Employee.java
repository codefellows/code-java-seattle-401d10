package com.codefellows.salmonCookies;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;

    String name;
    int payPerHour;

//     this modifies the table AND how the data is SELECTED from the table
    @ManyToOne
    CookieStore employedAt;

    public Employee(String name, int payPerHour, CookieStore employedAt) {
        this.name = name;
        this.payPerHour = payPerHour;
        this.employedAt = employedAt;
    }

    public Employee(){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPayPerHour() {
        return payPerHour;
    }
}
