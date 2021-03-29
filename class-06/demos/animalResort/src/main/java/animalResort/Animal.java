package animalResort;

public class Animal {
    String name;
    int age;
    static boolean needsToEat = true;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return String.format("Hi, my name is %s and I am %d years old", name, age);
    }

    public void makeNoise(){
        System.out.println("aaaaaaaaaa aaaaaaa aa aaaaaaa eeeee aaaaaaah");
    }
}
