package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Event
 * @author Marc
 */
@Entity
public class Event extends MappedEntity implements IEntity {
    
    private Long id;
    private Integer version;
    private String code;
    private String description;
    private Activity activityCondition;
    private Status statusCondition;
    private Integer levelCondition;
    private Integer goldCondition;
    private Integer healthCondition;
    private Integer manaCondition;
    private Boolean valueBelow;
    private Boolean valuePercentage;
    private Double healthModifier;
    private Double manaModifier;
    private Double experienceModifier;
    private Double goldModifier;
    private Double chance;
    private Double variance;
    
    public Event() {
        this.initialize();
    }
    
    private void initialize() {
        this.setId(0l);
        this.setVersion(0);
        this.setDescription(null);
        this.setActivityCondition(Activity.NONE);
        this.setStatusCondition(Status.NONE);
        this.setLevelCondition(0);
        this.setGoldCondition(0);
        this.setHealthCondition(0);
        this.setManaCondition(0);
        this.setValueBelow(true);
        this.setValuePercentage(true);
        this.setHealthModifier(0d);
        this.setManaModifier(0d);
        this.setExperienceModifier(0d);
        this.setGoldModifier(0d);
        this.setChance(0d);
        this.setVariance(0d);
    }
    
    @Override
    @Transient
    public Model getModel() {
        return Model.EVENT;
    }
    
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
        this.set("id", this.id);
    }

    @Override
    @Version
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
        this.set("version", this.version);
    }

    @Column(unique = true, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        this.set("code", this.code);
    }
    
    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.set("description", this.description);
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Activity getActivityCondition() {
        return activityCondition;
    }

    public void setActivityCondition(Activity activityCondition) {
        this.activityCondition = activityCondition;
        this.set("activityCondition", this.activityCondition);
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Status getStatusCondition() {
        return statusCondition;
    }

    public void setStatusCondition(Status statusCondition) {
        this.statusCondition = statusCondition;
        this.set("statusCondition", this.statusCondition);
    }

    @Column
    public Integer getLevelCondition() {
        return levelCondition;
    }

    public void setLevelCondition(Integer levelCondition) {
        this.levelCondition = levelCondition;
        this.set("levelCondition", this.levelCondition);
    }
    
    @Column
    public Integer getGoldCondition() {
        return goldCondition;
    }

    public void setGoldCondition(Integer goldCondition) {
        this.goldCondition = goldCondition;
        this.set("goldCondition", this.goldCondition);
    }

    @Column
    public Integer getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(Integer healthCondition) {
        this.healthCondition = healthCondition;
        this.set("healthCondition", this.healthCondition);
    }

    @Column
    public Integer getManaCondition() {
        return manaCondition;
    }

    public void setManaCondition(Integer manaCondition) {
        this.manaCondition = manaCondition;
        this.set("manaCondition", this.manaCondition);
    }

    @Column
    public Boolean getValueBelow() {
        return valueBelow;
    }

    public void setValueBelow(Boolean valueBelow) {
        this.valueBelow = valueBelow;
        this.set("valueBelow", this.valueBelow);
    }

    @Column
    public Boolean getValuePercentage() {
        return valuePercentage;
    }

    public void setValuePercentage(Boolean valuePercentage) {
        this.valuePercentage = valuePercentage;
        this.set("valuePercentage", this.valuePercentage);
    }
    
    @Column
    public Double getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(Double healthModifier) {
        this.healthModifier = healthModifier;
        this.set("healthModifier", this.healthModifier);
    }

    @Column
    public Double getManaModifier() {
        return manaModifier;
    }

    public void setManaModifier(Double manaModifier) {
        this.manaModifier = manaModifier;
        this.set("manaModifier", this.manaModifier);
    }

    @Column
    public Double getExperienceModifier() {
        return experienceModifier;
    }

    public void setExperienceModifier(Double experienceModifier) {
        this.experienceModifier = experienceModifier;
        this.set("experienceModifier", this.experienceModifier);
    }

    @Column
    public Double getGoldModifier() {
        return goldModifier;
    }

    public void setGoldModifier(Double goldModifier) {
        this.goldModifier = goldModifier;
        this.set("goldModifier", this.goldModifier);
    }

    @Column
    public Double getChance() {
        return chance;
    }

    public void setChance(Double chance) {
        this.chance = chance;
        this.set("chance", this.chance);
    }

    @Column
    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
        this.set("variance", this.variance);
    }
    
    @Override
    public void updateMapping() {
        
    }
}
