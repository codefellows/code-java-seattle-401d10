package videoGame;

public class FlowerMonster implements EngageAble {
    public FlowerMonster(){
        Game.monsters.add(this);
    }

    @Override
    public String fight(EngageAble opponent) {
        return "flower power activate, pew pew";
    }

    @Override
    public String battleCry() {
        return "I am not sure";
    }
}
