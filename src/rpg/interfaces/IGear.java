package rpg.interfaces;

public interface IGear {
    int getAttackModifier();
    int getDefenseModifier();
    String getName();
    String getAdjective();
    String getNoun();

    public IGear combine(IGear other) throws Exception;
}
