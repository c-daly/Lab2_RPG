package rpg;


import rpg.interfaces.IGear;

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

    public Player(int attack, int defense) throws IllegalArgumentException {
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Negative ints");
        }
        attackValue = attack;
        defenseValue = defense;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public IGear chooseGear(List<IGear> gear) throws Exception {
        equipGear(gear.get(0));
        return gear.get(0);
    }

    private void equipGear(IGear gear) throws Exception {
        Random rand = new Random();

        if(gear instanceof HeadGear) {
            if(headGear.size() <= MAX_HEADGEAR) {
                headGear.add(gear);
            } else {
                int randInt = rand.nextInt(headGear.size());
                IGear tempGear = headGear.get(randInt);
                headGear.set(randInt, tempGear.combine(gear));
            }

        } else if(gear instanceof HandGear) {
            if(handGear.size() <= MAX_HANDGEAR) {
                handGear.add(gear);
            } else {
                int randInt = rand.nextInt(handGear.size());
                IGear tempGear = handGear.get(randInt);
                handGear.set(randInt, tempGear.combine(gear));
            }
        } else if(gear instanceof Footwear) {
            if(footwear.size() <= MAX_FOOTWEAR) {
                footwear.add(gear);
            } else {
                int randInt = rand.nextInt(footwear.size());
                IGear tempGear = footwear.get(randInt);
                footwear.set(randInt, tempGear.combine(gear));
            }
        } else {
           // throw unknown gear type exception
        }
    }

    public void printPlayerStatus() {
        System.out.println();
    }
}
