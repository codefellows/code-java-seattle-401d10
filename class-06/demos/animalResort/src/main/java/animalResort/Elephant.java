package animalResort;

public class Elephant extends Animal {
    public Elephant(String name, int age){
        super(name, age);
    };

    @Override
    public void makeNoise(){
        System.out.println("phreeeee phrroooo I want to play in the lazy river");
    }
}
