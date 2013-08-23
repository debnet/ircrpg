package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Action;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Return;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Index;

/**
 *
 * @author Marc
 */
@Entity(name = "Result")
public class Result extends MappedEntity implements IEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    
    @Index(name = "result_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar date;
    
    @Transient
    private Boolean success;
    
    @Index(name = "result_action")
    @Enumerated(value=EnumType.STRING)
    private Action action;
    
    // List instead of EnumSet because it needs to be sorted
    @CollectionOfElements
    @Enumerated(value=EnumType.STRING)
    private List<Return> returns;
    
    @OneToOne
    private Player player;
    private Double playerHealthChanges;
    private Double playerManaChanges;
    private Double playerGoldChanges;
    private Double playerExperienceChanges;
    
    @OneToOne
    private Player target;
    private Double targetHealthChanges;
    private Double targetManaChanges;
    private Double targetGoldChanges;
    private Double targetExperienceChanges;
    
    private double value;
    private String details;
    
    public Result() {
        this.setId(0l);
        this.setVersion(0);
        this.setDate(Calendar.getInstance());
        this.setSuccess(false);
        this.setAction(Action.NONE);
        this.setReturns(new ArrayList<Return>());
        this.setPlayer(null);
        this.setPlayerHealthChanges(0d);
        this.setPlayerManaChanges(0d);
        this.setPlayerGoldChanges(0d);
        this.setPlayerExperienceChanges(0d);
        this.setTarget(null);
        this.setTargetHealthChanges(0d);
        this.setTargetManaChanges(0d);
        this.setTargetGoldChanges(0d);
        this.setTargetExperienceChanges(0d);
        this.setValue(0d);
        this.setDetails(null);
    }
    
    public Result(Action action) {
        this();
        this.setAction(action);
    }
    
    public Result(Action action, Player player) {
        this(action);
        this.setPlayer(player);
    }
    
    public Result(Action action, Player player, Player target) {
        this(action, player);
        this.setTarget(target);
    }
        
    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(String.format("[%s] (%b) %s", 
            this.action, this.success, this.returns, this.value, this.details));
        if (this.success && this.player != null) {
            build.append('\n');
            build.append(String.format("\"%s\" ($ %+.2f ; XP %+.2f ; HP %+.2f ; MP %+.2f)", 
                this.player.getUsername(),
                this.playerGoldChanges, this.playerExperienceChanges,
                this.playerHealthChanges, this.playerManaChanges));
        }
        if (this.success && this.target != null) {
            build.append('\n');
            build.append(String.format("\"%s\" ($ %+.2f ; XP %+.2f ; HP %+.2f ; MP %+.2f)", 
                this.target.getUsername(),
                this.targetGoldChanges, this.targetExperienceChanges,
                this.targetHealthChanges, this.targetManaChanges));
        }
        return build.toString();
    }
    
    /* Getters & setters */
    
    @Override
    public Model getModel() {
        return Model.RESULT;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
        this.set("date", this.date);
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
        this.set("success", this.success);
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
        this.set("action", this.action);
    }

    public List<Return> getReturns() {
        return returns;
    }

    public void setReturns(List<Return> returns) {
        this.returns = returns;
        this.set("returns", this.returns);
    }
    
    public void addReturn(Return r) {
        if (!this.returns.contains(r)) {
            this.returns.add(r);
            this.set("returns", this.returns);
        }
    }
    
    public void removeReturn(Return r) {
        if (this.returns.contains(r)) {
            this.returns.remove(r);
            this.set("returns", this.returns);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Double getPlayerHealthChanges() {
        return playerHealthChanges;
    }

    public void setPlayerHealthChanges(Double playerHealthChanges) {
        this.playerHealthChanges = playerHealthChanges;
        this.set("playerHealthChanges", this.playerHealthChanges);
    }
    
    public void addPlayerHealthChanges(Double playerHealthChanges) {
        this.playerHealthChanges += playerHealthChanges;
        this.set("playerHealthChanges", this.playerHealthChanges);
    }

    public Double getPlayerManaChanges() {
        return playerManaChanges;
    }

    public void setPlayerManaChanges(Double playerManaChanges) {
        this.playerManaChanges = playerManaChanges;
        this.set("playerManaChanges", this.playerManaChanges);
    }
    
    public void addPlayerManaChanges(Double playerManaChanges) {
        this.playerManaChanges += playerManaChanges;
        this.set("playerManaChanges", this.playerManaChanges);
    }

    public Double getPlayerGoldChanges() {
        return playerGoldChanges;
    }

    public void setPlayerGoldChanges(Double playerGoldChanges) {
        this.playerGoldChanges = playerGoldChanges;
        this.set("playerGoldChanges", this.playerGoldChanges);
    }
    
    public void addPlayerGoldChanges(Double playerGoldChanges) {
        this.playerGoldChanges += playerGoldChanges;
        this.set("playerGoldChanges", this.playerGoldChanges);
    }

    public Double getPlayerExperienceChanges() {
        return playerExperienceChanges;
    }

    public void setPlayerExperienceChanges(Double playerExperienceChanges) {
        this.playerExperienceChanges = playerExperienceChanges;
        this.set("playerExperienceChanges", this.playerExperienceChanges);
    }
    
    public void addPlayerExperienceChanges(Double playerExperienceChanges) {
        this.playerExperienceChanges += playerExperienceChanges;
        this.set("playerExperienceChanges", this.playerExperienceChanges);
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public Double getTargetHealthChanges() {
        return targetHealthChanges;
    }

    public void setTargetHealthChanges(Double targetHealthChanges) {
        this.targetHealthChanges = targetHealthChanges;
        this.set("targetHealthChanges", this.targetHealthChanges);
    }
    
    public void addTargetHealthChanges(Double enemyHealthChanges) {
        this.targetHealthChanges += enemyHealthChanges;
        this.set("targetHealthChanges", this.targetHealthChanges);
    }

    public Double getTargetManaChanges() {
        return targetManaChanges;
    }

    public void setTargetManaChanges(Double targetManaChanges) {
        this.targetManaChanges = targetManaChanges;
        this.set("targetManaChanges", this.targetManaChanges);
    }
    
    public void addTargetManaChanges(Double enemyManaChanges) {
        this.targetManaChanges += enemyManaChanges;
        this.set("targetManaChanges", this.targetManaChanges);
    }

    public Double getTargetGoldChanges() {
        return targetGoldChanges;
    }

    public void setTargetGoldChanges(Double targetGoldChanges) {
        this.targetGoldChanges = targetGoldChanges;
        this.set("targetGoldChanges", this.targetGoldChanges);
    }
    
    public void addTargetGoldChanges(Double enemyGoldChanges) {
        this.targetGoldChanges += enemyGoldChanges;
        this.set("targetGoldChanges", this.targetGoldChanges);
    }

    public Double getTargetExperienceChanges() {
        return targetExperienceChanges;
    }

    public void setTargetExperienceChanges(Double targetExperienceChanges) {
        this.targetExperienceChanges = targetExperienceChanges;
        this.set("targetExperienceChanges", this.targetExperienceChanges);
    }
    
    public void addTargetExperienceChanges(Double enemyExperienceChanges) {
        this.targetExperienceChanges += enemyExperienceChanges;
        this.set("targetExperienceChanges", this.targetExperienceChanges);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        this.set("value", this.value);
    }
    
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
        this.set("details", this.details);
    }

    /* Specific methods */
    
    public void addPlayerGoldChanges(int gold) {
        this.playerGoldChanges += gold;
        this.set("playerGoldChanges", this.playerGoldChanges);
    }
    
    public boolean hasReturn(Return ret) {
        return this.getReturns().contains(ret);
    }
    
    public void addReturnList(List<Return> list) {
        this.returns.addAll(list);
        this.set("returns", this.returns);
    }
    
    @Override
    public void updateMapping() {
        this.set("player", this.player);
        this.set("target", this.target);
    }
}
