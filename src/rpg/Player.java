package rpg;


import rpg.interfaces.IGear;

import javax.lang.model.type.UnknownTypeException;
import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private static final int MAX_HEADGEAR = 1;
    private static final int MAX_FOOTWEAR = 2;
    private static final int MAX_HANDGEAR = 2;

    private final int attackValue;
    private final int defenseValue;
    private int attackModifier;
    private int defenseModifier;
    private List<IGear> headGear;
    private List<IGear> footwear;
    private List<IGear> handGear;

    /**
     * @param attack
     * @param defense
     * @throws IllegalArgumentException
     */
    public Player(int attack, int defense) throws IllegalArgumentException {
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Negative ints");
        }
        attackValue = attack;
        defenseValue = defense;
    }

    /**
     * @return
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
        return attackModifier;
    }

    /**
     * @return summed defensive bonus of all gear
     */
    public int getDefenseModifier() {
        return defenseModifier;
    }

    /**
     * @param gear the list of IGear items from which the player can choose
     * @return the IGear item chosen
     * @throws IllegalArgumentException if gear is null, Exception if execution
     *          completes without choosing gear.
     */
    public IGear chooseGear(List<IGear> gear) throws Exception {
        if (gear == null) {
            throw new IllegalArgumentException("Gear argument must not be null.");
        }

        // sort gear by attack value
        // select all gear matching highest attack value
        // select all the gear for which there is room in inventory
        // if more than one has the high attack value, take the one with the highest defense
        // if more than one ties on defense, select randomly
        List<IGear> attackGear = gear.stream().sorted(IGear.attackComparator).toList();
        List<IGear> potentialAttackItems = new ArrayList<>();
        int highestAttack = 0;
        for (IGear item : attackGear) {
            if (item.getAttackModifier() < highestAttack) {
                // this is a sorted list, so we've passed all items with the highest attack value
                if (potentialAttackItems.size() == 1) {
                    // we've only got one item so we can return it
                    return potentialAttackItems.get(0);
                } else {
                    // we have more than one item; we'll have to look at defense
                    break;
                }
            } else {
                potentialAttackItems.add(item);
            }
        }

        List<IGear> defenseGear = potentialAttackItems.stream().sorted(IGear.defenseComparator).toList();
        List<IGear> potentialDefenseItems = new ArrayList<>();
        int highestDefense = 0;
        for(IGear item : defenseGear) {
            // if this value is lower, it's because we've already passed through the max value
            // and we're no longer interested in the other pieces of gear
            if(item.getDefenseModifier() < highestDefense) {
                // we only have one candidate, so let's return it
               if(potentialDefenseItems.size() == 1) {
                   return potentialDefenseItems.get(0);
               } else {
                   // We have multiple elements with the highest defense value
                   // so we'll send a random one back
                   Random rand = new Random();
                   int randInt = rand.nextInt(potentialDefenseItems.size());
                   return potentialDefenseItems.get(randInt);
               }
            } else {
                // This is a higher defense value, so let's add it
                potentialDefenseItems.add(item);
            }
        }
        // shouldn't get here
        // throwing vanilla Exception, rather than custom
        throw new Exception("Choose gear loop unexpectedly completed without returning gear");
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
     *          be equipped without combining.
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

    //private int getAppropriateItemsMax(IGear gear) throws UnknownTypeException {
    //    if (gear instanceof HandGear) {
    //        return MAX_HANDGEAR;
    //    } else if (gear instanceof HeadGear) {
    //        return MAX_HEADGEAR;
    //    } else if (gear instanceof Footwear) {
    //        return MAX_FOOTWEAR;
    //    }

    //    throw new IllegalArgumentException("Gear arg is of unknown type: " + gear.getClass().toString());
   // }

    public String getPlayerDescription() {
        return "Description goes here";
    }
}
