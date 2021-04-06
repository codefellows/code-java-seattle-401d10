package com.codefellows.salmonCookies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
1. Give your class an Entity annotation
2. Give your class an id, generated value of IDENTITY
3. It needs a default constructor
(it will create an empty object when retrieving from the storage layer and then assign the properties)
 4. create a <EntityClassName>Repository Interface that extends JpaRepository

jdbc setup
1. Add the dependencies to build.gradle
2. add application.properties spring.datasource properties

 */

@Entity
public class CookieStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public String name;
    int total = 0;
    private int[] sales = new int[4];
    int minSales;
    int maxSales;

    public CookieStore(String name, int minSales, int maxSales) {
        this.name = name;
        this.minSales = minSales;
        this.maxSales = maxSales;
        this.calculateSales();
    }

    CookieStore(){}

    private void calculateSales(){
        for(int i = 0; i < sales.length; i++){
            double rando = Math.random();
            int sold = (int)(rando * (maxSales - minSales)) + minSales;
            sales[i] = sold;
            total += sold;
        }
    }

    public int getTotal() {
        return this.total;
    }

    public int[] getSales() {
        return sales;
    }

    public long getId() {
        return id;
    }
}
