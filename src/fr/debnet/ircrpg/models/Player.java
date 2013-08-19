package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Activity;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Status;
import fr.debnet.ircrpg.Config;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
public class Player extends MappedEntity {

    @Id
    private Long id;
    @Version
    private Integer version;
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private String hostname;
    private Boolean online;
    private Boolean admin;
    @Enumerated(EnumType.STRING)
    private Activity activity;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer level;
    private Double experience;
    private Double currentHealth;
    private Integer maxHealth;
    private Double currentMana;
    private Integer maxMana;
    private Integer attack;
    private Integer defense;
    private Double gold;
    private Integer skillPoints;
    private Integer healthPotions;
    private Integer manaPotions;
    private Integer remedyPotions;
    private Integer activityDuration;
    private Integer statusDuration;
    
    @Index(name = "player_lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar lastUpdate;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Item> items;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Spell> spells;
    
    // Statistics
    private Integer timeIngame;
    private Integer timeWorking;
    private Integer timeResting;
    private Integer timeTraining;
    private Integer moneySpent;
    private Double moneyStolen;
    private Double damageTaken;
    private Double damageGiven;
    private Integer deaths;
    private Integer kills;

    public Player() {
        
    }
    
    @Override
    protected void setDefaultValues() {
        this.setId(0l);
        this.setVersion(0);
        this.setUsername(null);
        this.setPassword(null);
        this.setNickname(null);
        this.setHostname(null);
        this.setOnline(false);
        this.setAdmin(false);
        this.setActivity(Activity.NONE);
        this.setStatus(Status.NORMAL);
        this.setLevel(1);
        this.setExperience(0d);
        this.setMaxHealth(Config.START_HEALTH);
        this.setCurrentHealth((double) this.getMaxHealth());
        this.setMaxMana(Config.START_MANA);
        this.setCurrentMana((double) this.getMaxMana());
        this.setAttack(Config.START_ATTACK);
        this.setDefense(Config.START_DEFENSE);
        this.setGold((double) Config.START_GOLD);
        this.setSkillPoints(0);
        this.setHealthPotions(0);
        this.setManaPotions(0);
        this.setRemedyPotions(0);
        this.setActivityDuration(0);
        this.setStatusDuration(0);
        this.setLastUpdate(Calendar.getInstance());
        this.setItems(new ArrayList<Item>());
        this.setSpells(new ArrayList<Spell>());
        
        this.setTimeIngame(0);
        this.setTimeWorking(0);
        this.setTimeResting(0);
        this.setTimeTraining(0);
        this.setMoneySpent(0);
        this.setMoneyStolen(0d);
        this.setDamageTaken(0d);
        this.setDamageGiven(0d);
        this.setDeaths(0);
        this.setKills(0);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.set("username", this.username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.set("password", this.password);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.set("nickname", this.nickname);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
        this.set("hostname", this.hostname);
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
        this.set("admin", this.admin);
    }
    
    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
        this.set("online", this.online);
    }
    
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.set("activity", this.activity);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.set("status", this.status);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        this.set("level", this.level);
    }
    
    public void addLevel(Integer level) {
        this.level += level;
        this.set("level", this.level);
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
        this.set("experience", this.experience);
    }
    
    public void addExperience(Double experience) {
        this.experience += experience;
        this.set("experience", this.experience);
    }

    public Double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Double currentHealth) {
        this.currentHealth = currentHealth;
        this.set("currentHealth", this.currentHealth);
    }
    
    public void addHealth(Double health) {
        this.currentHealth += health;
        this.set("currentHealth", this.currentHealth);
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
        this.set("maxHealth", this.maxHealth);
    }
    
    public void addMaxHealth(Integer maxHealth) {
        this.maxHealth += maxHealth;
        this.set("maxHealth", this.maxHealth);
    }

    public Double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(Double currentMana) {
        this.currentMana = currentMana;
        this.set("currentMana", this.currentMana);
    }
    
    public void addMana(Double mana) {
        this.currentMana += mana;
        this.set("currentMana", this.currentMana);
    }

    public Integer getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(Integer maxMana) {
        this.maxMana = maxMana;
        this.set("maxMana", this.maxMana);
    }
    
    public void addMaxMana(Integer maxMana) {
        this.maxMana += maxMana;
        this.set("maxMana", this.maxMana);
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
        this.set("attack", this.attack);
    }
    
    public void addAttack(Integer attack) {
        this.attack += attack;
        this.set("attack", this.attack);
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
        this.set("defense", this.defense);
    }
    
    public void addDefense(Integer defense) {
        this.defense += defense;
        this.set("defense", this.defense);
    }

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
        this.set("gold", this.gold);
    }
    
    public void addGold(Double gold) {
        this.gold += gold;
        this.set("gold", this.gold);
    }

    public Integer getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(Integer skillPoints) {
        this.skillPoints = skillPoints;
        this.set("skillPoints", this.skillPoints);
    }
    
    public void addSkillPoints(Integer skillPoints) {
        this.skillPoints += skillPoints;
        this.set("skillPoints", this.skillPoints);
    }

    public Calendar getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Calendar lastUpdate) {
        this.lastUpdate = lastUpdate;
        this.set("lastUpdate", this.lastUpdate);
    }

    public Integer getHealthPotions() {
        return healthPotions;
    }

    public void setHealthPotions(Integer healthPotions) {
        this.healthPotions = healthPotions;
        this.set("healthPotions", this.healthPotions);
    }
    
    public void addHealthPotions(Integer healthPotions) {
        this.healthPotions += healthPotions;
        this.set("healthPotions", this.healthPotions);
    }

    public Integer getManaPotions() {
        return manaPotions;
    }

    public void setManaPotions(Integer manaPotions) {
        this.manaPotions = manaPotions;
        this.set("manaPotions", this.manaPotions);
    }
    
    public void addManaPotions(Integer manaPotions) {
        this.manaPotions += manaPotions;
        this.set("manaPotions", this.manaPotions);
    }

    public Integer getRemedyPotions() {
        return remedyPotions;
    }

    public void setRemedyPotions(Integer remedyPotions) {
        this.remedyPotions = remedyPotions;
        this.set("remedyPotions", this.remedyPotions);
    }
    
    public void addRemedyPotions(Integer remedyPotions) {
        this.remedyPotions += remedyPotions;
        this.set("remedyPotions", this.remedyPotions);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        this.set("items", this.items);
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.set("items", this.items);
    }
    
    public void removeItem(Item item) {
        this.items.remove(item);
        this.set("items", this.items);
    }
    
    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
        this.set("spells", this.spells);
    }
    
    public void addSpell(Spell spell) {
        this.spells.add(spell);
        this.set("spells", this.spells);
    }
    
    public void removeSpell(Spell spell) {
        this.spells.remove(spell);
        this.set("spells", this.spells);
    }

    public Integer getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(Integer activityDuration) {
        this.activityDuration = activityDuration;
        this.set("activityDuration", this.activityDuration);
    }
    
    public void addActivityDuration(Integer activityDuration) {
        this.activityDuration += activityDuration;
        this.set("activityDuration", this.activityDuration);
    }

    public Integer getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(Integer statusDuration) {
        this.statusDuration = statusDuration;
        this.set("statusDuration", this.statusDuration);
    }
    
    public void addStatusDuration(Integer statusDuration) {
        this.statusDuration += statusDuration;
        this.set("statusDuration", this.statusDuration);
    }

    public Integer getTimeIngame() {
        return timeIngame;
    }

    public void setTimeIngame(Integer timeIngame) {
        this.timeIngame = timeIngame;
        this.set("timeIngame", this.timeIngame);
    }
    
    public void addTimeIngame(Integer timeIngame) {
        this.timeIngame += timeIngame;
        this.set("timeIngame", this.timeIngame);
    }

    public Integer getTimeWorking() {
        return timeWorking;
    }

    public void setTimeWorking(Integer timeWorking) {
        this.timeWorking = timeWorking;
        this.set("timeWorking", this.timeWorking);
    }
    
    public void addTimeWorking(Integer timeWorking) {
        this.timeWorking += timeWorking;
        this.set("timeWorking", this.timeWorking);
    }

    public Integer getTimeResting() {
        return timeResting;
    }

    public void setTimeResting(Integer timeResting) {
        this.timeResting = timeResting;
        this.set("timeResting", this.timeResting);
    }
    
    public void addTimeResting(Integer timeResting) {
        this.timeResting += timeResting;
        this.set("timeResting", this.timeResting);
    }

    public Integer getTimeTraining() {
        return timeTraining;
    }

    public void setTimeTraining(Integer timeTraining) {
        this.timeTraining = timeTraining;
        this.set("timeTraining", this.timeTraining);
    }
    
    public void addTimeTraining(Integer timeTraining) {
        this.timeTraining += timeTraining;
        this.set("timeTraining", this.timeTraining);
    }
    
    public Integer getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(Integer moneySpent) {
        this.moneySpent = moneySpent;
        this.set("moneySpent", this.moneySpent);
    }
    
    public void addMoneySpent(Integer moneySpent) {
        this.moneySpent += moneySpent;
        this.set("moneySpent", this.moneySpent);
    }

    public Double getMoneyStolen() {
        return moneyStolen;
    }

    public void setMoneyStolen(Double moneyStolen) {
        this.moneyStolen = moneyStolen;
        this.set("moneyStolen", this.moneyStolen);
    }
    
    public void addMoneyStolen(Double moneyStolen) {
        this.moneyStolen += moneyStolen;
        this.set("moneyStolen", this.moneyStolen);
    }

    public Double getDamageTaken() {
        return damageTaken;
    }

    public void setDamageTaken(Double damageTaken) {
        this.damageTaken = damageTaken;
        this.set("damageTaken", this.damageTaken);
    }
    
    public void addDamageTaken(Double damageTaken) {
        this.damageTaken += damageTaken;
        this.set("damageTaken", this.damageTaken);
    }

    public Double getDamageGiven() {
        return damageGiven;
    }

    public void setDamageGiven(Double damageGiven) {
        this.damageGiven = damageGiven;
        this.set("damageGiven", this.damageGiven);
    }
    
    public void addDamageGiven(Double damageGiven) {
        this.damageGiven += damageGiven;
        this.set("damageGiven", this.damageGiven);
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
        this.set("deaths", this.deaths);
    }
    
    public void addDeaths(Integer deaths) {
        this.deaths += deaths;
        this.set("deaths", this.deaths);
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
        this.set("kills", this.kills);
    }
    
    public void addKills(Integer kills) {
        this.kills += kills;
        this.set("kills", this.kills);
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
    
    @Override
    public String toString() {
        return String.format("%s (%s)", this.nickname, this.username);
    }
    
    /* Specific methods */
    
    public void addHealth(int health) {
        this.currentHealth += health;
        this.set("currentHealth", this.currentHealth);
    }

    public void addMana(int mana) {
        this.currentMana += mana;
        this.set("currentMana", this.currentMana);
    }

    public void addGold(int gold) {
        this.gold += gold;
        this.set("gold", this.gold);
    }
}
