package animalResort;

// By extending Dog , I am type Dog, Animal, Walkable and AustrailianKelpie
public class AustrailianKelpie extends Dog{
    static boolean sheepWalker = true;
    Sheep mySheepFriend;

    public AustrailianKelpie(boolean trained, String name, int age) {
        super(trained, name, age);
    }

    @Override
    public void walk(String destination){
//         super == Dog
        if(mySheepFriend == null) super.walk(destination);
        else System.out.println(String.format(
                "I walk upon %s to get to %s like a chariot",
                mySheepFriend.name,
                destination
        ));
    }
}
