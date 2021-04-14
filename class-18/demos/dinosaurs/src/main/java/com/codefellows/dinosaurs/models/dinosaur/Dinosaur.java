package com.codefellows.dinosaurs.models.dinosaur;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dinosaur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String color;
    int scales;
    boolean carnivore;


//    The valley of friendship bakery
//    dinosaurs can bake a cake for each other
//    If every dinosaur bakes a cake for every other dinosaur we have a cake party pot luck

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
        name="cake_givers",
        joinColumns = {@JoinColumn(name="giver")},
        inverseJoinColumns = {@JoinColumn(name="receiver")}
    )
    Set<Dinosaur> dinosaursIHaveGivenACakeTo = new HashSet<>();

    @ManyToMany(mappedBy = "dinosaursIHaveGivenACakeTo")
    Set<Dinosaur> dinosaursIHaveReceivedCakeFrom = new HashSet<>();

//    The choice of a set will invoke distinct on the relationship


    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setScales(int scales) {
        this.scales = scales;
    }

    public void setCarnivore(boolean carnivore) {
        this.carnivore = carnivore;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getScales() {
        return scales;
    }

    public boolean isCarnivore() {
        return carnivore;
    }

    public Set<Dinosaur> getDinosaursIHaveGivenACakeTo() {
        return dinosaursIHaveGivenACakeTo;
    }

    public Set<Dinosaur> getDinosaursIHaveReceivedCakeFrom() {
        return dinosaursIHaveReceivedCakeFrom;
    }

    public long getId() {
        return id;
    }
}
