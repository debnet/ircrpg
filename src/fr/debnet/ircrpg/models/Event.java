package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import java.util.Objects;
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
 *
 * @author Marc
 */
@Entity(name = "Event")
public class Event extends MappedEntity implements IEntity {
    
    private Long id;
    private Integer version;
    private String description;
    private Activity activityCondition;
    private Status statusCondition;
    private Integer levelCondition;
    private Integer healthModifier;
    private Integer manaModifier;
    private Integer experienceModifier;
    private Integer goldModifier;
    private Integer chance;
    
    public Event() {
        this.initialize();
    }
    
    private void initialize() {
        this.setId(0l);
        this.setVersion(0);
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
        this.set("description", this.description);
    }

    @Column
    public Integer getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(Integer healthModifier) {
        this.healthModifier = healthModifier;
        this.set("healthModifier", this.healthModifier);
    }

    @Column
    public Integer getManaModifier() {
        return manaModifier;
    }

    public void setManaModifier(Integer manaModifier) {
        this.manaModifier = manaModifier;
        this.set("manaModifier", this.manaModifier);
    }

    @Column
    public Integer getExperienceModifier() {
        return experienceModifier;
    }

    public void setExperienceModifier(Integer experienceModifier) {
        this.experienceModifier = experienceModifier;
        this.set("experienceModifier", this.experienceModifier);
    }

    @Column
    public Integer getGoldModifier() {
        return goldModifier;
    }

    public void setGoldModifier(Integer goldModifier) {
        this.goldModifier = goldModifier;
        this.set("goldModifier", this.goldModifier);
    }

    @Column
    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
        this.set("chance", this.chance);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public void updateMapping() {
        
    }
}
