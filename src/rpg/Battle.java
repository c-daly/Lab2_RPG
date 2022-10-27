package rpg;

import rpg.interfaces.IGear;

import java.util.Comparator;
import java.util.List;

public class Battle {
    /**
     * instance of Player, goes first in the battle
     */
    private final Player player1;
    /**
     * instance of Player, goes second in the battle
     */
    private final Player player2;
    /**
     * List of instances of IGear representing the available gear
     * for players to equip during the battle.
     */
    private final List<rpg.interfaces.IGear> gear;

    /**
     * @param player1 instance of Player that will take first turn.
     * @param player2 instance of Player that will take second turn.
     * @param gear List of IGear instances that will represent the gear
     *             available for players to equip.
     * @throws IllegalArgumentException when one of the args is null
     */
    public Battle(Player player1, Player player2, List<IGear> gear) throws IllegalArgumentException {
        if (player1 == null ||
            player2 == null ||
            gear == null) {
            throw new IllegalArgumentException("All args to Battle constructor must be non-null.");
        }
        this.player1 = player1;
        this.player2 = player2;
        // Empty gear list is ok, winner will just be based on natural stats
        this.gear = gear;
    }

    /**
     * Handles the actual battle.  Iterates until gear is gone
     * and determines a winner by invoking finalizeGame().
     */
    public void runBattle() {
        int turn = 0;

        while (gear.size() > 0) {
            processTurn();
        }
        finalizeGame();
    }

    /**
     * Helper method that aggregates the battle finalization
     * actions of printing the final battle status and
     * declaring a winner.
     */
    private void finalizeGame() {
        printBattleStatus();
        declareWinner();
    }

    /**
     * Bulk of the work happens here.
     * We print the battle status and check the gear collection, just in case
     * we got here by mistake.  Player1 chooses and equips a piece of gear
     * and we check again if any is left.  If available, player2 chooses and
     * equips a piece of gear.  Finally, when no gear remains, we call the battle.
     */
    private void processTurn() {
        printBattleStatus();
        // we shouldn't have gotten here if there is no gear remaining,
        // but just in case..
        if (gearRemaining()) {
            try {
                IGear gearChosen1 = player1.chooseGear(gear);
                gear.remove(gearChosen1);
            } catch (Exception e) {
                // log and continue
            }
        }
        // Did player1 take the last item?
        if (gearRemaining()) {
            try {
                IGear gearChosen2 = player2.chooseGear(gear);
                gear.remove(gearChosen2);
            } catch (Exception e) {
                // log and continue
            }
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

    /**
     *  Prints descriptive information about the battle state.
     */
    private void printBattleStatus() {

        System.out.println(player1.getPlayerDescription());
        System.out.println(player2.getPlayerDescription());
    }

    /**
     * Helper method just checks if the gearbag is empty.
     *
     * @return boolean indicating if any gear remains.
     */
    private boolean gearRemaining() {
        return (!gear.isEmpty());
    }

    /**
     * Method prints a string indicating the winner of the battle.
     */
    private void declareWinner() {
        System.out.println("There was a winner!");
    }

}
