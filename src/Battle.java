import rpg.Player;
import rpg.interfaces.IGear;

import java.util.Comparator;
import java.util.List;

public class Battle {
    private final Player player1;
    private final Player player2;
    private final List<IGear> gear;

    public Battle(Player player1, Player player2, List<IGear> gear) {
        this.player1 = player1;
        this.player2 = player2;
        // Empty gear list is ok, winner will just be based on natural stats
        this.gear = gear;
    }

    public void runBattle() {
        int turn = 0;

        while (gear.size() > 0) {
            processTurn();
        }
        finalizeGame();
    }

    private void finalizeGame() {
        printBattleStatus();
        declareWinner();
    }

    private void processTurn() {
        printBattleStatus();
        IGear gearChosen1 = player1.chooseGear(gear);
        gear.remove(gearChosen1);
        // Did player1 take the last item?
        if (gearRemaining()) {
            IGear gearChosen2 = player2.chooseGear(gear);
            gear.remove(gearChosen2);
        }
    }

    private List<IGear> sortBy(String sortField) throws Exception {
        // TODO: This needs to be cleaned up
        Comparator<IGear> comparator = IGear.attackComparator;
        switch (sortField.toLowerCase()) {
            case "attack":
                comparator = IGear.attackComparator;
            case "defense":
                comparator = IGear.defenseComparator;
        }
        return gear.stream().sorted(comparator).toList();
    }

    private void printBattleStatus() {
        player1.printPlayerStatus();
        player2.printPlayerStatus();
    }
    private boolean gearRemaining() {
        return (!gear.isEmpty());
    }

    private void declareWinner() {
        System.out.println("There was a winner!");
    }

}
