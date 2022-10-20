package rpg;

import rpg.base.AbstractGear;
import rpg.interfaces.IGear;

public class HeadGear extends AbstractGear {

    public  HeadGear(int attack, int defense, String adjective, String noun) {
        super(attack, defense, adjective, noun);
    }

    @Override
    public IGear combine(IGear otherGear) {

        boolean sameTypes = otherGear instanceof HeadGear;
        if (!sameTypes) {
            // throw exception
        }
        return new HeadGear(getAttackModifier(), getDefenseModifier(), otherGear.getAdjective(), getNoun());
    }
}
