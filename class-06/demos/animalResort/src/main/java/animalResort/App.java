/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package animalResort;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
//        Meet (instantiate) a few animals
//        They will go to the resort (all)
//        They will make friends on the beach (domesicated)
//        They will eat fun things (allergies, carnivores)
//        They will get groomed (impossible?)
//        Float in a lazy river (can they swim? like water?)

        List<Animal> animalsAtResort = new LinkedList<>();

        Animal animalUnknown = new Animal("Aaron", 1000);
        System.out.println(animalUnknown);
        animalUnknown.makeNoise();

        Dog ginger = new Dog(true, "Ginger", 2);
        ginger.showOff();
        ginger.makeNoise();
        System.out.println(ginger);
//         If Dog `extends` Animal,
//         Dog will have all the properties of Animal in addition to its own
//        Dog will have all the methods of Animal in addition to its own
//        I can store a Dog as an Animal

        Animal animalVersionGinger = ginger;
//        animalVersionGinger.showOff(); // Animal's cant show off because they are not type Dog
        animalVersionGinger.makeNoise();
        System.out.println(animalVersionGinger);

        Elephant horton = new Elephant("horton", 30);
        System.out.println(horton);
        horton.makeNoise();
        Animal animalVersionHorton = new Elephant("horton2", 30);
        System.out.println(animalVersionHorton);
        animalVersionHorton.makeNoise();

//        Add aaron, horton, and ginger to the same list
        animalsAtResort.add(horton);
        animalsAtResort.add(animalUnknown);
        animalsAtResort.add(ginger);
        for(Animal animal : animalsAtResort){
            System.out.println(animal.name);
            animal.makeNoise();
            System.out.println(animal);
            }

        System.out.println(" ======================== ");
        playInRiver(animalUnknown);
        playInRiver(horton);
        playInRiver(animalVersionHorton);
        playInRiver(ginger);
        playInRiver(animalVersionGinger);

        System.out.println(" ========================");
        System.out.println("interface stuff");
        ginger.markTerritory();
        ginger.walk("new york");
        WalkAble gingerAsInterface = ginger;
        gingerAsInterface.markTerritory();
        gingerAsInterface.walk("ohio");

        Sheep sheepy = new Sheep("sheepy the sheep", 5);
        sheepy.markTerritory();
        sheepy.walk("the park");
        WalkAble walkAbleSheep = new Sheep("delilah", 90);
        walkAbleSheep.markTerritory();

        WalkAble[] walkableAnimals = {ginger, sheepy};
        for(WalkAble walkableAnimal : walkableAnimals){
            walkableAnimal.walk("the aquarium");
        }

        AustrailianKelpie maya = new AustrailianKelpie(true, "Maya", 3);
        maya.walk("paris");
        maya.mySheepFriend = sheepy;
        maya.walk("aquarium (with a sheep friend)");

    }

    public static void playInRiver(Animal animal){
        System.out.println("yay I am " + animal.name + " and I am playing in the river");
    }
}
