package dankery;

import java.util.ArrayList;
import java.util.List;

public class Bakery {
    public String name;
    public ArrayList<Pastry> pastries = new ArrayList<>();
//    we need to know name of the bakery(static vs instance) (instance : not static)
//    What they sell (pastries) (static vs instance) (instance)

    public Bakery(String name){
        this.name = name;
    }

//    not potato
    public String toString(){
        return name + " sells : " + pastries.toString();
    }

}
