import rpg.Player;
import rpg.interfaces.IGear;

import java.util.Comparator;
import java.util.List;

public class Battle {
    private final int MAX_TURNS = 10;
    private Player player1;
    private Player player2;
    private List<IGear> gear;

    Comparator<IGear> attackComparator = (gear1, gear2) -> ((Integer) gear1.getAttackModifier()).compareTo((Integer) gear2.getAttackModifier());
    Comparator<IGear> defenseComparator = (gear1, gear2) -> ((Integer) gear1.getDefenseModifier()).compareTo((Integer) gear2.getDefenseModifier());
    public Battle(Player player1, Player player2, List<IGear> gear) {
        this.player1 = player1;
        this.player2 = player2;
        // Empty gear list is ok, winner will just be based on natural stats
        this.gear = gear;
    }
    public void runBattle() {
        int turn = 0;

        while (turn <= MAX_TURNS) {
            processTurn();
            turn++;
        }
    }

    private void processTurn() {
        if (gearRemaining()) {
            player1.equipGear(player1.chooseGear(gear));
            player2.equipGear(player1.chooseGear(gear));
        } else {
            declareWinner();
        }
    }

    private boolean gearRemaining() {
        return (!gear.isEmpty());
    }

    private void declareWinner() {}
    private List<IGear> sortBy(String sortField) throws Exception {
        Comparator comparator = attackComparator;
        switch(sortField.toLowerCase()) {
            case "attack":
                comparator = attackComparator;
            case "defense":
                comparator = defenseComparator;

            return gear.stream().sorted(comparator).toList();
        }
    }
}
