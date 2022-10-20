package rpg;

import rpg.interfaces.IGear;

import java.util.List;

public class Player {
    private static final int MAX_HEADGEAR = 1;
    private static final int MAX_FOOTWEAR = 2;
    private static final int MAX_HANDGEAR = 2;

    private int attackValue;
    private int defenseValue;
    private int attackModifier;
    private int defenseModifier;
    private List<HeadGear> headGear;
    private List<Footwear> footwear;
    private List<HandGear> handGear;

    public Player(int attack, int defense) {
        attackValue = attack;
        defenseValue = defense;
    }

    public IGear chooseGear(List<IGear> gear) {
        return gear.get(0);
    }

    public void equipGear(IGear gear) {
        if(gear instanceof HeadGear) {
            if(headGear.size() <= MAX_HEADGEAR) {
                headGear.add((HeadGear) gear);
            }

        } else if(gear instanceof HandGear) {
            if(handGear.size() <= MAX_HEADGEAR) {
                handGear.add((HandGear) gear);
            }
        } else if(gear instanceof Footwear) {
            if(footwear.size() <= MAX_HEADGEAR) {
                footwear.add((Footwear) gear);
            }
        } else {
           // throw unknown gear type exception
        }
    }
}
