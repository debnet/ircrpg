package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.interfaces.MappedEntity;
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
public class Spell extends MappedEntity {

    @Id
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean isAdmin;
    private Integer goldCost;
    private Integer minLevel;
    private Boolean isSelf;
    private Double healthDamage;
    private Double manaCost;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer statusDuration;

    public Spell() {
        
    }
    
    @Override
    protected void setDefaultValues() {
        this.setId(0l);
        this.setVersion(0);
        this.setCode(null);
        this.setName(null);
        this.setDescription(null);
        this.setIsAdmin(false);
        this.setGoldCost(0);
        this.setMinLevel(0);
        this.setIsSelf(false);
        this.setHealthDamage(0d);
        this.setManaCost(0d);
        this.setStatus(Status.NONE);
        this.setStatusDuration(0);
    }
    
    @Override
    public Model getModel() {
        return Model.SPELL;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        this.set("isAdmin", this.isAdmin);
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

    public Boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
        this.set("isSelf", this.isSelf);
    }

    public Double getHealthDamage() {
        return healthDamage;
    }

    public void setHealthDamage(Double healthDamage) {
        this.healthDamage = healthDamage;
        this.set("healthDamage", this.healthDamage);
    }

    public Double getManaCost() {
        return manaCost;
    }

    public void setManaCost(Double manaCost) {
        this.manaCost = manaCost;
        this.set("manaCost", this.manaCost);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.set("status", this.status);
    }

    public Integer getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(Integer statusDuration) {
        this.statusDuration = statusDuration;
        this.set("statusDuration", this.statusDuration);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.code != null ? this.code.hashCode() : 0);
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
        final Spell other = (Spell) obj;
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
