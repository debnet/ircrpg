/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Marc
 */
@javax.persistence.Entity
public class Spell extends Entity {

    @Id
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean isAdmin = false;
    private Integer goldCost = 0;
    private Integer minLevel = 0;
    private Boolean isSelf = false;
    private Double healthDamage = 0d;
    private Double manaCost = 0d;
    @Enumerated(EnumType.STRING)
    private Status status = Status.NONE;
    private Integer statusDuration = 0;

    public Spell() {
        
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public Boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
    }

    public Double getHealthDamage() {
        return healthDamage;
    }

    public void setHealthDamage(Double healthDamage) {
        this.healthDamage = healthDamage;
    }

    public Double getManaCost() {
        return manaCost;
    }

    public void setManaCost(Double manaCost) {
        this.manaCost = manaCost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(Integer statusDuration) {
        this.statusDuration = statusDuration;
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
