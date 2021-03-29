package animalResort;

import java.util.ArrayList;

//extends: build a parent child relationship to a class
// super: calls the constructor of the parent

//implements : build a parent child relationship TO and interface
// an interface's rule is that the child MUST override all the methods
// We use this when we know the behavior may need to be different so we don't have a universal
//     way that we could write the methods, so we make the child write them

public class Dog extends Animal implements WalkAble {
    boolean trained;
    ArrayList<String> skills = new ArrayList<>();

    public void showOff(){
        if(skills.size() == 0) {
            System.out.println("i can pat my head and rub my doggy belly");
            return;
        }
        int skillIndex = (int)(Math.random() * skills.size());
        System.out.println(skills.get(skillIndex));
    }

    public Dog(boolean trained, String name, int age){
        super(name, age);
        this.trained = trained;
    }

    @Override
    public void makeNoise(){
        System.out.println("woof woof I want to go outside and play on the beach");
    }

    @Override
    public void walk(String destination) {
        System.out.println(String.format("yay we are walking to %s", destination));
    }

    @Override
    public void markTerritory() {
        System.out.println("excuse me please my good person, i must attend to a formality of business");
    }
}
