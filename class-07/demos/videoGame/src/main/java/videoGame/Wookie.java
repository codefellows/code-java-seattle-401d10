package videoGame;

public class Wookie implements Movable, EngageAble{
    public Wookie(){
        Game.monsters.add(this);
    }

    @Override
    public String fight(EngageAble opponent) {
        return "wookie fights";
    }

    @Override
    public String battleCry() {
        return "rhheeehgrheeegh";
    }

    @Override
    public void walk() {
        System.out.println("wookie walking here");
    }

    @Override
    public void crawl() {
        System.out.println("wookie crawling here");
    }
}
