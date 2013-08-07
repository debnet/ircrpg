/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.Config;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;
import org.hibernate.annotations.Index;

/**
 *
 * @author Marc
 */
@javax.persistence.Entity
public class Player extends Entity {

    @Id
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private String hostname;
    private Boolean online = false;
    private Boolean admin = false;
    @Enumerated(EnumType.STRING)
    private Activity activity = Activity.NONE;
    @Enumerated(EnumType.STRING)
    private Status status = Status.NORMAL;
    private Integer level = 1;
    private Double experience = 0d;
    private Double currentHealth = (double) Config.START_HEALTH;
    private Integer maxHealth = Config.START_HEALTH;
    private Double currentMana = (double) Config.START_MANA;
    private Integer maxMana = Config.START_MANA;
    private Integer attack = Config.START_ATTACK;
    private Integer defense = Config.START_DEFENSE;
    private Double gold = (double) Config.START_GOLD;
    private Integer skillPoints = 0;
    private Integer healthPotions = 0;
    private Integer manaPotions = 0;
    private Integer remedyPotions = 0;
    private Integer activityDuration = 0;
    private Integer statusDuration = 0;
    
    // Statistics
    private Integer timeIngame = 0;
    private Integer timeWorking = 0;
    private Integer timeResting = 0;
    private Integer timeTraining = 0;
    private Integer moneySpent = 0;
    private Double moneyStolen = 0d;
    private Double damageTaken = 0d;
    private Double damageGiven = 0d;
    private Integer deaths = 0;
    private Integer kills = 0;
    
    @Index(name = "player_lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar lastUpdate = Calendar.getInstance();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Item> items;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Spell> spells;

    public Player() {
        this.items = new ArrayList<Item>();
        this.spells = new ArrayList<Spell>();
    }
    
    @Override
    public Model getModel() {
        return Model.PLAYER;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
    
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public void addLevel(Integer level) {
        this.level += level;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }
    
    public void addExperience(Double experience) {
        this.experience += experience;
    }

    public Double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Double currentHealth) {
        this.currentHealth = currentHealth;
    }
    
    public void addHealth(Double health) {
        this.currentHealth += health;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }
    
    public void addMaxHealth(Integer maxHealth) {
        this.maxHealth += maxHealth;
    }

    public Double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(Double currentMana) {
        this.currentMana = currentMana;
    }
    
    public void addMana(Double mana) {
        this.currentMana += mana;
    }

    public Integer getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(Integer maxMana) {
        this.maxMana = maxMana;
    }
    
    public void addMaxMana(Integer maxMana) {
        this.maxMana += maxMana;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }
    
    public void addAttack(Integer attack) {
        this.attack += attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
    
    public void addDefense(Integer defense) {
        this.defense += defense;
    }

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }
    
    public void addGold(Double gold) {
        this.gold += gold;
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(Integer skillPoints) {
        this.skillPoints = skillPoints;
    }
    
    public void addSkillPoints(Integer skillPoints) {
        this.skillPoints += skillPoints;
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(Integer healthPotions) {
        this.healthPotions = healthPotions;
    }
    
    public void addHealthPotions(Integer healthPotions) {
        this.healthPotions += healthPotions;
    }

    public Integer getManaPotions() {
        return manaPotions;
    }

    public void setManaPotions(Integer manaPotions) {
        this.manaPotions = manaPotions;
    }
    
    public void addManaPotions(Integer manaPotions) {
        this.manaPotions += manaPotions;
    }

    public Integer getRemedyPotions() {
        return remedyPotions;
    }

    public void setRemedyPotions(Integer remedyPotions) {
        this.remedyPotions = remedyPotions;
    }
    
    public void addRemedyPotions(Integer remedyPotions) {
        this.remedyPotions += remedyPotions;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
    
    public void removeItem(Item item) {
        this.items.remove(item);
    }
    
    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }
    
    public void addSpell(Spell spell) {
        this.spells.add(spell);
    }
    
    public void removeSpell(Spell spell) {
        this.spells.remove(spell);
    }

    public Integer getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(Integer activityDuration) {
        this.activityDuration = activityDuration;
    }
    
    public void addActivityDuration(Integer activityDuration) {
        this.activityDuration += activityDuration;
    }

    public Integer getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(Integer statusDuration) {
        this.statusDuration = statusDuration;
    }
    
    public void addStatusDuration(Integer statusDuration) {
        this.statusDuration += statusDuration;
    }

    public Integer getTimeIngame() {
        return timeIngame;
    }

    public void setTimeIngame(Integer timeIngame) {
        this.timeIngame = timeIngame;
    }
    
    public void addTimeIngame(Integer timeIngame) {
        this.timeIngame += timeIngame;
    }

    public Integer getTimeWorking() {
        return timeWorking;
    }

    public void setTimeWorking(Integer timeWorking) {
        this.timeWorking = timeWorking;
    }
    
    public void addTimeWorking(Integer timeWorking) {
        this.timeWorking += timeWorking;
    }

    public Integer getTimeResting() {
        return timeResting;
    }

    public void setTimeResting(Integer timeResting) {
        this.timeResting = timeResting;
    }
    
    public void addTimeResting(Integer timeResting) {
        this.timeResting += timeResting;
    }

    public Integer getTimeTraining() {
        return timeTraining;
    }

    public void setTimeTraining(Integer timeTraining) {
        this.timeTraining = timeTraining;
    }
    
    public void addTimeTraining(Integer timeTraining) {
        this.timeTraining += timeTraining;
    }
    
    public Integer getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(Integer moneySpent) {
        this.moneySpent = moneySpent;
    }
    
    public void addMoneySpent(Integer moneySpent) {
        this.moneySpent += moneySpent;
    }

    public Double getMoneyStolen() {
        return moneyStolen;
    }

    public void setMoneyStolen(Double moneyStolen) {
        this.moneyStolen = moneyStolen;
    }
    
    public void addMoneyStolen(Double moneyStolen) {
        this.moneyStolen += moneyStolen;
    }

    public Double getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(Double damageTaken) {
        this.damageTaken = damageTaken;
    }
    
    public void addDamageTaken(Double damageTaken) {
        this.damageTaken += damageTaken;
    }

    public Double getDamageGiven() {
        return damageGiven;
    }

    public void setDamageGiven(Double damageGiven) {
        this.damageGiven = damageGiven;
    }
    
    public void addDamageGiven(Double damageGiven) {
        this.damageGiven += damageGiven;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }
    
    public void addDeaths(Integer deaths) {
        this.deaths += deaths;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }
    
    public void addKills(Integer kills) {
        this.kills += kills;
    }
    
    public Modifiers getModifiers() {
        return new Modifiers(this);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.username != null ? this.username.hashCode() : 0);
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
        final Player other = (Player) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    public void addHealth(int health) {
        this.currentHealth += health;
    }

    public void addMana(int mana) {
        this.currentMana += mana;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", this.nickname, this.username);
    }
}
