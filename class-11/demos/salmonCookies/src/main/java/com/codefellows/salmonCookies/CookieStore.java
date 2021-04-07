package com.codefellows.salmonCookies;

public class CookieStore {
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
}
