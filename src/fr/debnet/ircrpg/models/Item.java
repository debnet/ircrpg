package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.enums.Equipment;
import fr.debnet.ircrpg.enums.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Marc
 */
@Entity
public class Item implements IEntity {

    @Id
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Equipment type;
    private Boolean isAdmin = false;
    private Integer stock = 0;
    private Integer goldCost = 0;
    private Integer minLevel = 0;
    private Integer healthModifier = 0;
    private Integer manaModifier = 0;
    private Integer attackModifier = 0;
    private Integer defenseModifier = 0;
    private Double attackAccuracyModifier = 0d;
    private Double defenseAccuracyModifier = 0d;
    private Double magicAccuracyModifier = 0d;
    private Double experienceRateModifier = 0d;
    private Double goldRateModifier = 0d;
    private Double healthRateModifier = 0d;
    private Double healthPotionRegenModifier = 0d;
    private Double manaPotionRegenModifier = 0d;
    private Double poisonEffectModifier = 0d;
    private Double stealingChanceModifier = 0d;
    private Double stealingGoldModifier = 0d;

    public Item() {
        
    }
    
    @Override
    public Model getModel() {
        return Model.ITEM;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Equipment getType() {
        return type;
    }

    public void setType(Equipment type) {
        this.type = type;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public void addStock(Integer stock) {
        this.stock += stock;
    }
    
    public Integer getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(Integer goldCost) {
        this.goldCost = goldCost;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(Integer healthModifier) {
        this.healthModifier = healthModifier;
    }

    public Integer getManaModifier() {
        return manaModifier;
    }

    public void setManaModifier(Integer manaModifier) {
        this.manaModifier = manaModifier;
    }

    public Integer getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(Integer attackModifier) {
        this.attackModifier = attackModifier;
    }

    public Integer getDefenseModifier() {
        return defenseModifier;
    }

    public void setDefenseModifier(Integer defenseModifier) {
        this.defenseModifier = defenseModifier;
    }

    public Double getAttackAccuracyModifier() {
        return attackAccuracyModifier;
    }

    public void setAttackAccuracyModifier(Double attackAccuracyModifier) {
        this.attackAccuracyModifier = attackAccuracyModifier;
    }

    public Double getDefenseAccuracyModifier() {
        return defenseAccuracyModifier;
    }

    public void setDefenseAccuracyModifier(Double defenseAccuracyModifier) {
        this.defenseAccuracyModifier = defenseAccuracyModifier;
    }

    public Double getMagicAccuracyModifier() {
        return magicAccuracyModifier;
    }

    public void setMagicAccuracyModifier(Double magicAccuracyModifier) {
        this.magicAccuracyModifier = magicAccuracyModifier;
    }

    public Double getExperienceRateModifier() {
        return experienceRateModifier;
    }

    public void setExperienceRateModifier(Double experienceRateModifier) {
        this.experienceRateModifier = experienceRateModifier;
    }

    public Double getGoldRateModifier() {
        return goldRateModifier;
    }

    public void setGoldRateModifier(Double goldRateModifier) {
        this.goldRateModifier = goldRateModifier;
    }

    public Double getHealthRateModifier() {
        return healthRateModifier;
    }

    public void setHealthRateModifier(Double healthRateModifier) {
        this.healthRateModifier = healthRateModifier;
    }

    public Double getHealthPotionRegenModifier() {
        return healthPotionRegenModifier;
    }

    public void setHealthPotionRegenModifier(Double healthPotionRegenModifier) {
        this.healthPotionRegenModifier = healthPotionRegenModifier;
    }

    public Double getManaPotionRegenModifier() {
        return manaPotionRegenModifier;
    }

    public void setManaPotionRegenModifier(Double manaPotionRegenModifier) {
        this.manaPotionRegenModifier = manaPotionRegenModifier;
    }

    public Double getPoisonEffectModifier() {
        return poisonEffectModifier;
    }

    public void setPoisonEffectModifier(Double poisonEffectModifier) {
        this.poisonEffectModifier = poisonEffectModifier;
    }

    public Double getStealingChanceModifier() {
        return stealingChanceModifier;
    }

    public void setStealingChanceModifier(Double stealingChanceModifier) {
        this.stealingChanceModifier = stealingChanceModifier;
    }

    public Double getStealingGoldModifier() {
        return stealingGoldModifier;
    }

    public void setStealingGoldModifier(Double stealingGoldModifier) {
        this.stealingGoldModifier = stealingGoldModifier;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.code);
    }
}
