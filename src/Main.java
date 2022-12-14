import rpg.Battle;
import rpg.Footwear;
import rpg.HandGear;
import rpg.Player;
import rpg.interfaces.IGear;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IGear> gear = new ArrayList<>();
        Player player1 = new Player(1, 1);
        Player player2 = new Player(1, 1);
        Battle battle = new Battle(player1, player2, gear);
        IGear footGear = new Footwear(3, 3, "test1", "test2");
        IGear handGear = new HandGear(2, 2, "Awesome", "handgear");
        gear.add(footGear);
        gear.add(handGear);
        battle.runBattle();
    }

}