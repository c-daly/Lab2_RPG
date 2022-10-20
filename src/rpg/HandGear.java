package rpg;

import rpg.base.AbstractGear;
import rpg.interfaces.IGear;

public class HandGear extends AbstractGear {
    public HandGear(int attack, int defense, String adjective, String noun) {
        super(attack, defense, adjective, noun);
    }
    @Override
    public IGear combine(IGear otherGear) throws Exception {
        return new HandGear(getAttackModifier(), getDefenseModifier(), otherGear.getAdjective(), getNoun());
    }
}
