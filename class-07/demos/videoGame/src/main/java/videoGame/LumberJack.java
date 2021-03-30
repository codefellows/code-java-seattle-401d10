package videoGame;

public class LumberJack implements EngageAble, Movable {

    @Override
    public String fight(EngageAble opponent){
        String outcome;
        double rando = Math.random();
        if(rando > 0.5) outcome = "beat the opponent";
        else outcome = "died";

        return String.format(
                "I yell %s, then they yelled %s, then I %s",
                this.battleCry(),
                opponent.battleCry(),
                outcome
        );
    }

    @Override
    public String battleCry() {
        return "Sgarrrrrr";
    }


    @Override
    public void walk() {
//        50% chance to find any Monster
//        Monster fights you
        double rando = Math.random();
        if(rando > 0.5) System.out.println("all safe");
        else {
            if(Game.monsters.size() == 0) {
                System.out.println("i thought I saw a monster but I was mistaken");
                return;
            };
            EngageAble monster = Game.monsters.get(
                    (int)(Math.random() * Game.monsters.size())
            );
            System.out.println(monster.fight(this));

        }
    }

    @Override
    public void crawl() {
        // 20% chance to find a monster
        //        you fight the monster

        double rando = Math.random();
        if(rando > 0.8) System.out.println("all safe");
        else {
            if(Game.monsters.size() == 0) {
                System.out.println("i thought I saw a monster but I was mistaken");
                return;
            };
            EngageAble monster = Game.monsters.get(
                    (int)(Math.random() * Game.monsters.size())
            );
            System.out.println(this.fight(monster));

        }
    }
}
