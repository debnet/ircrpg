package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Equipment;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Marc
 */
@Entity(name = "Item")
public class Item extends MappedEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String code;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Equipment type;
    private Boolean isAdmin;
    private Integer stock;
    private Integer goldCost;
    private Integer minLevel;
    private Integer healthModifier;
    private Integer manaModifier;
    private Integer attackModifier;
    private Integer defenseModifier;
    private Double attackAccuracyModifier;
    private Double defenseAccuracyModifier;
    private Double magicAccuracyModifier;
    private Double experienceRateModifier;
    private Double goldRateModifier;
    private Double healthRateModifier;
    private Double healthPotionRegenModifier;
    private Double manaPotionRegenModifier;
    private Double poisonEffectModifier;
    private Double stealingChanceModifier;
    private Double stealingGoldModifier;

    public Item() {
        this.setId(0l);
        this.setVersion(0);
        this.setCode(null);
        this.setName(null);
        this.setDescription(null);
        this.setType(Equipment.NONE);
        this.setIsAdmin(false);
        this.setStock(0);
        this.setGoldCost(0);
        this.setMinLevel(0);
        this.setHealthModifier(0);
        this.setManaModifier(0);
        this.setAttackModifier(0);
        this.setDefenseModifier(0);
        this.setAttackAccuracyModifier(0d);
        this.setDefenseAccuracyModifier(0d);
        this.setMagicAccuracyModifier(0d);
        this.setExperienceRateModifier(0d);
        this.setGoldRateModifier(0d);
        this.setHealthRateModifier(0d);
        this.setHealthPotionRegenModifier(0d);
        this.setManaPotionRegenModifier(0d);
        this.setPoisonEffectModifier(0d);
        this.setStealingChanceModifier(0d);
        this.setStealingGoldModifier(0d);        
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
        this.set("id", this.id);
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
        this.set("version", this.version);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.set("code", this.code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.set("name", this.name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.set("description", this.description);
    }

    public Equipment getType() {
        return type;
    }

    public void setType(Equipment type) {
        this.type = type;
        this.set("type", this.type);
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.set("isAdmin", this.isAdmin);
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        this.set("stock", this.stock);
    }
    
    public void addStock(Integer stock) {
        this.stock += stock;
        this.set("stock", this.stock);
    }
    
    public Integer getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(Integer goldCost) {
        this.goldCost = goldCost;
        this.set("goldCost", this.goldCost);
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
        this.set("minLevel", this.minLevel);
    }

    public Integer getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(Integer healthModifier) {
        this.healthModifier = healthModifier;
        this.set("healthModifier", this.healthModifier);
    }

    public Integer getManaModifier() {
        return manaModifier;
    }

    public void setManaModifier(Integer manaModifier) {
        this.manaModifier = manaModifier;
        this.set("manaModifier", this.manaModifier);
    }

    public Integer getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(Integer attackModifier) {
        this.attackModifier = attackModifier;
        this.set("attackModifier", this.attackModifier);
    }

    public Integer getDefenseModifier() {
        return defenseModifier;
    }

    public void setDefenseModifier(Integer defenseModifier) {
        this.defenseModifier = defenseModifier;
        this.set("defenseModifier", this.defenseModifier);
    }

    public Double getAttackAccuracyModifier() {
        return attackAccuracyModifier;
    }

    public void setAttackAccuracyModifier(Double attackAccuracyModifier) {
        this.attackAccuracyModifier = attackAccuracyModifier;
        this.set("attackAccuracyModifier", this.attackAccuracyModifier);
    }

    public Double getDefenseAccuracyModifier() {
        return defenseAccuracyModifier;
    }

    public void setDefenseAccuracyModifier(Double defenseAccuracyModifier) {
        this.defenseAccuracyModifier = defenseAccuracyModifier;
        this.set("defenseAccuracyModifier", this.defenseAccuracyModifier);
    }

    public Double getMagicAccuracyModifier() {
        return magicAccuracyModifier;
    }

    public void setMagicAccuracyModifier(Double magicAccuracyModifier) {
        this.magicAccuracyModifier = magicAccuracyModifier;
        this.set("magicAccuracyModifier", this.magicAccuracyModifier);
    }

    public Double getExperienceRateModifier() {
        return experienceRateModifier;
    }

    public void setExperienceRateModifier(Double experienceRateModifier) {
        this.experienceRateModifier = experienceRateModifier;
        this.set("experienceRateModifier", this.experienceRateModifier);
    }

    public Double getGoldRateModifier() {
        return goldRateModifier;
    }

    public void setGoldRateModifier(Double goldRateModifier) {
        this.goldRateModifier = goldRateModifier;
        this.set("goldRateModifier", this.goldRateModifier);
    }

    public Double getHealthRateModifier() {
        return healthRateModifier;
    }

    public void setHealthRateModifier(Double healthRateModifier) {
        this.healthRateModifier = healthRateModifier;
        this.set("healthRateModifier", this.healthRateModifier);
    }

    public Double getHealthPotionRegenModifier() {
        return healthPotionRegenModifier;
    }

    public void setHealthPotionRegenModifier(Double healthPotionRegenModifier) {
        this.healthPotionRegenModifier = healthPotionRegenModifier;
        this.set("healthPotionRegenModifier", this.healthPotionRegenModifier);
    }

    public Double getManaPotionRegenModifier() {
        return manaPotionRegenModifier;
    }

    public void setManaPotionRegenModifier(Double manaPotionRegenModifier) {
        this.manaPotionRegenModifier = manaPotionRegenModifier;
        this.set("manaPotionRegenModifier", this.manaPotionRegenModifier);
    }

    public Double getPoisonEffectModifier() {
        return poisonEffectModifier;
    }

    public void setPoisonEffectModifier(Double poisonEffectModifier) {
        this.poisonEffectModifier = poisonEffectModifier;
        this.set("poisonEffectModifier", this.poisonEffectModifier);
    }

    public Double getStealingChanceModifier() {
        return stealingChanceModifier;
    }

    public void setStealingChanceModifier(Double stealingChanceModifier) {
        this.stealingChanceModifier = stealingChanceModifier;
        this.set("stealingChanceModifier", this.stealingChanceModifier);
    }

    public Double getStealingGoldModifier() {
        return stealingGoldModifier;
    }

    public void setStealingGoldModifier(Double stealingGoldModifier) {
        this.stealingGoldModifier = stealingGoldModifier;
        this.set("stealingGoldModifier", this.stealingGoldModifier);
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
    
    @Override
    public void refresh() {
        
    }
}
