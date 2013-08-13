package fr.debnet.ircrpg.models;

/**
 *
 * @author Marc
 */
public class Modifiers {

    private int health;
    private int mana;
    private int attack;
    private int defense;
    private double attackAccuracy;
    private double defenseAccuracy;
    private double magicAccuracy;
    private double experienceRate;
    private double goldRate;
    private double healthRate;
    private double potionHealth;
    private double potionMana;
    private double poisonEffect;
    private double theftChance;
    private double theftGold;

    public Modifiers(Player player) {
        for (Item item : player.getItems()) {
            this.attack += item.getAttackModifier();
            this.defense += item.getDefenseModifier();
            this.health += item.getHealthModifier();
            this.mana += item.getManaModifier();
            this.experienceRate += item.getExperienceRateModifier();
            this.goldRate += item.getGoldRateModifier();
            this.healthRate += item.getHealthRateModifier();
            this.attackAccuracy += item.getAttackAccuracyModifier();
            this.defenseAccuracy += item.getDefenseAccuracyModifier();
            this.magicAccuracy += item.getMagicAccuracyModifier();
            this.potionHealth += item.getHealthPotionRegenModifier();
            this.potionMana += item.getManaPotionRegenModifier();
            this.poisonEffect += item.getPoisonEffectModifier();
            this.theftChance += item.getStealingChanceModifier();
            this.theftGold += item.getStealingGoldModifier();
        }
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public double getAttackAccuracy() {
        return attackAccuracy;
    }

    public void setAttackAccuracy(double attackAccuracy) {
        this.attackAccuracy = attackAccuracy;
    }

    public double getDefenseAccuracy() {
        return defenseAccuracy;
    }

    public void setDefenseAccuracy(double defenseAccuracy) {
        this.defenseAccuracy = defenseAccuracy;
    }

    public double getMagicAccuracy() {
        return magicAccuracy;
    }

    public void setMagicAccuracy(double magicAccuracy) {
        this.magicAccuracy = magicAccuracy;
    }

    public double getExperienceRate() {
        return experienceRate;
    }

    public double getGoldRate() {
        return goldRate;
    }

    public double getHealthRate() {
        return healthRate;
    }

    public double getPotionHealth() {
        return potionHealth;
    }

    public double getPotionMana() {
        return potionMana;
    }

    public double getPoisonEffect() {
        return poisonEffect;
    }

    public double getTheftChance() {
        return theftChance;
    }

    public double getTheftGold() {
        return theftGold;
    }
}
