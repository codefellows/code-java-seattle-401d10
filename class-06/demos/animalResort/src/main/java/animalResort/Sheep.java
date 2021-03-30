package animalResort;

public class Sheep extends Animal implements WalkAble{
    public Sheep(String name, int age){
        super(name, age);
    }


    @Override
    public void walk(String destination) {
        System.out.println("baaaaa, i loathe walking, why do we gotta go to " + destination);
    }

    @Override
    public void markTerritory() {
        System.out.println("mua ha haha, I have claimed this territory by means of my peepee");
    }
}
