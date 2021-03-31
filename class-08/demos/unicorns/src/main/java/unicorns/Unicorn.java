package unicorns;

import java.util.ArrayList;

public class Unicorn {
    String color;
    boolean sparkles;
    int age;
    String name;
    ArrayList<Cupcake> bakedGoods = new ArrayList<>();

    public Unicorn(String color, boolean sparkles, int age, String name) {
        this.color = color;
        this.sparkles = sparkles;
        this.age = age;
        this.name = name;

    }

    public void makeNoise(){
        System.out.println("roar.");
    }

    public String toString(){
        return String.format(
                "I am %s, a %s unicorn who is %d years old and sparkles : %b",
                name,
                color,
                age,
                sparkles
                );
    }
}
