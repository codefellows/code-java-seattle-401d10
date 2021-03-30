package videoGame;

public class Tree implements EngageAble{

    String name;

    @Override
    public String fight(EngageAble opponent) {
//        Ambush Time
        String outcome;
        double rando = Math.random();
        if(rando > 0.2) outcome = "you are dead";
        else outcome = "you narrowly escape with your life";
        return String.format(
                "Surprise MotherTrucker, %s, here's a thwack from %s, %s",
                this.battleCry(),
                this.name,
                outcome
        );
    }

    @Override
    public String battleCry() {
        return "I'll teach YOU to leaf me alone";
    }

    public Tree(String name){
        this.name = name;
        Game.monsters.add(this);
    }
}
