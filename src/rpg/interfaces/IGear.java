package rpg.interfaces;

public interface IGear {
    public int getAttackModifier();
    public int getDefenseModifier();
    public String getName();
    public String getAdjective();
    public String getNoun();

    public IGear combine(IGear other);
}
