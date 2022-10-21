import rpg.Battle;
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
        battle.runBattle();
    }

}