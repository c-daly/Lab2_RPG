package rpg;


import rpg.interfaces.IGear;

import javax.lang.model.type.UnknownTypeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private static final int MAX_HEADGEAR = 1;
    private static final int MAX_FOOTWEAR = 2;
    private static final int MAX_HANDGEAR = 2;

    private final int attackValue;
    private final int defenseValue;
    private List<IGear> headGear = new ArrayList<>();
    private List<IGear> footwear = new ArrayList<>();
    private List<IGear> handGear = new ArrayList<>();

    /**
     * @param attack int representing player's innate attack value.
     * @param defense int representing player's innate defense value.
     * @throws IllegalArgumentException if either int is negative.
     */
    public Player(int attack, int defense) throws IllegalArgumentException {
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Negative ints");
        }
        attackValue = attack;
        defenseValue = defense;
    }

    /**
     * @return an int representing the player's attack value without bonuses from gear
     */
    public int getAttackValue() {
        return attackValue;
    }

    /**
     * @return defenseValue, the innate defense value of the player
     */
    public int getDefenseValue() {
        return defenseValue;
    }

    /**
     * @return summed attack bonus for all gear
     */
    public int getAttackModifier() {
        return sumGearAttackValues();
    }

    /**
     * @return summed defensive bonus of all gear
     */
    public int getDefenseModifier() {
        return sumGearDefenseValues();
    }

    /**
     * @param gear the list of IGear items from which the player can choose
     * @return the IGear item chosen
     * @throws IllegalArgumentException if gear is null, Exception if execution
     *                                  completes without choosing gear.
     */
    public IGear chooseGear(List<IGear> gear) throws IllegalArgumentException {
        if (gear == null) {
            throw new IllegalArgumentException("Gear argument must not be null.");
        }

        // First we'll look through the gear sorted by attack order.
        // If the current item can be equipped without combining, we'll use it.
        // if not, keep going through the gear list.
        List<IGear> attackGear = gear.stream().sorted(IGear.attackComparator).toList();
        List<IGear> potentialAttackItems = new ArrayList<>();

        int highestAttack = 0;

        for (IGear item : attackGear) {
            if (confirmRoomForGearType(item)) {
                if (item.getAttackModifier() < highestAttack) {
                    // this is a sorted list, so we've passed all items with the highest attack value
                    if (potentialAttackItems.size() == 1) {
                        // we've only got one item so we can return it
                        equipGear(potentialAttackItems.get(0));
                        return potentialAttackItems.get(0);
                    }
                } else {
                    // We must have a high attack value.  Add it to the list.
                    potentialAttackItems.add(item);
                    highestAttack = item.getAttackModifier();
                }
            }
        }

        List<IGear> defenseGear = gear.stream().sorted(IGear.defenseComparator).toList();
        List<IGear> potentialDefenseItems = new ArrayList<>();

        int highestDefense = 0;

        // We're going to do the same thing for defense
        for (IGear item : defenseGear) {
            // check we can wear this item without combining.
            if (confirmRoomForGearType(item)) {
                // if this value is lower, it's because we've already passed through the max value
                // and we're no longer interested in the other pieces of gear
                if (item.getDefenseModifier() < highestDefense) {
                    // we only have one candidate, so let's return it
                    if (potentialDefenseItems.size() == 1) {
                        equipGear(potentialAttackItems.get(0));
                        return potentialDefenseItems.get(0);
                    }
                } else {
                    // This is a higher defense value, so let's add it
                    potentialDefenseItems.add(item);
                    highestDefense = item.getDefenseModifier();
                }
            }
        }

        // We couldn't find a free spot for any gear
        // so we're returning a random item.
        Random rand = new Random();
        int randomIndex = rand.nextInt(gear.size());
        equipGear(gear.get(randomIndex));
        return gear.get(randomIndex);
    }

    /**
     * Equips the given piece of gear to the correct wear location, combining,
     * if necessary.
     *
     * @param gear the IGear item to be equipped
     * @throws IllegalArgumentException if gear is null
     */
    private void equipGear(IGear gear) throws IllegalArgumentException {
        if (gear == null) {
            throw new IllegalArgumentException("Gear argument must not be null");
        }

        List<IGear> gearList = getAppropriateGearList(gear);

        if (confirmRoomForGearType(gear)) {
            gearList.add(gear);
        } else {
            Random rand = new Random();
            int randInt = rand.nextInt(gearList.size());
            IGear tempGear = gearList.get(randInt);
            gearList.set(randInt, tempGear.combine(gear));
        }
    }

    /**
     * @param gear the gear item we want the matching list for.
     * @return List<IGear> of the appropriate gear type.
     * @throws UnknownTypeException if the gear is not of a recognized type
     */
    private List<IGear> getAppropriateGearList(IGear gear) throws UnknownTypeException {
        if (gear instanceof HandGear) {
            return handGear;
        } else if (gear instanceof HeadGear) {
            return headGear;
        } else if (gear instanceof Footwear) {
            return footwear;
        }

        throw new IllegalArgumentException("Gear arg is of unknown type: " + gear.getClass().toString());
    }

    /**
     * Checks to see if a given piece of gear could be equipped
     * without having to combine.
     *
     * @param gear the piece of gear to potentially equip
     * @return boolean indicating whether the piece of gear can
     * be equipped without combining.
     */
    private boolean confirmRoomForGearType(IGear gear) {
        if (gear instanceof HandGear) {
            return handGear.size() < MAX_HANDGEAR;
        } else if (gear instanceof HeadGear) {
            return headGear.size() < MAX_HEADGEAR;
        } else if (gear instanceof Footwear) {
            return footwear.size() < MAX_FOOTWEAR;
        }
        throw new IllegalArgumentException("Gear arg is of unknown type: " + gear.getClass().toString());
    }

    /**
     * @return the sum of attack bonuses for all equipped gear
     */
    private int sumGearAttackValues() {
        return(
                handGear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum) +
                headGear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum) +
                footwear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum)
        );
    }

    /**
     *
     * @return the sum of defense bonuses for all equipped gear.
     */
    private int sumGearDefenseValues() {
        return(
                handGear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum) +
                headGear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum) +
                footwear.stream().map(IGear::getAttackModifier).reduce(0, Integer::sum)
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Native attack: %d, Native defense: %d\n", getAttackValue(), getDefenseValue()));
        sb.append("\nPlayer Gear:\n");

        sb.append(handGear.stream().map(IGear::toString).reduce("", String::concat));
        sb.append(headGear.stream().map(IGear::toString).reduce("", String::concat));
        sb.append(footwear.stream().map(IGear::toString).reduce("", String::concat));

        return sb.toString();
    }
}
