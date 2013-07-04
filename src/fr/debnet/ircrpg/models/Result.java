/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.Strings;
import fr.debnet.ircrpg.enums.Action;
import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.enums.Return;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Entity
public class Result implements Serializable, IEntity {
    
    @Id
    private Long id;
    @Version
    private Integer version;
    
    @Index(name = "result_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar date;
    
    @Transient
    private Boolean success = false;
    
    @Index(name = "result_action")
    @Enumerated(value=EnumType.STRING)
    private Action action = Action.NONE;
    
    // List instead of EnumSet because it needs to be sorted
    @CollectionOfElements
    @Enumerated(value=EnumType.STRING)
    private List<Return> returns;
    
    @OneToOne
    private Player player;
    private Double playerHealthChanges = 0d;
    private Double playerManaChanges = 0d;
    private Double playerGoldChanges = 0d;
    private Double playerExperienceChanges = 0d;
    
    @OneToOne
    private Player target;
    private Double targetHealthChanges = 0d;
    private Double targetManaChanges = 0d;
    private Double targetGoldChanges = 0d;
    private Double targetExperienceChanges = 0d;
    
    private double value = 0d;
    private String details;
    
    public Result() {
        this.date = Calendar.getInstance();
    }
    
    public Result(Action action) {
        this();
        this.date = Calendar.getInstance();
        this.returns = new ArrayList<Return>();
        this.action = action;
    }
    
    public Result(Action action, Player player) {
        this(action);
        this.date = Calendar.getInstance();
        this.player = player;
    }
    
    public Result(Action action, Player player, Player target) {
        this(action);
        this.date = Calendar.getInstance();
        this.player = player;
        this.target = target;
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
    
    public String getMessage() {
        StringBuilder build = new StringBuilder(); 
        for (Return r : this.getReturns()) {
            build.append(this.formatString(r.getText()).trim());
            build.append(" ");
        }
        return build.toString();
    }
    
    private String formatString(String string) {
        String value = string;
        // Player values
        if (this.player != null) {
            value = value.replaceAll("<player.nickname>", this.player.getNickname());
            value = value.replaceAll("<player.hp>", String.format("%.2f", Math.abs(this.getPlayerHealthChanges())));
            value = value.replaceAll("<player.mp>", String.format("%.2f", Math.abs(this.getPlayerManaChanges())));
            value = value.replaceAll("<player.xp>", String.format("%.2f", Math.abs(this.getPlayerExperienceChanges())));
            value = value.replaceAll("<player.gold>", String.format("%.2f", Math.abs(this.getPlayerGoldChanges())));
            value = value.replaceAll("<player.level>", String.format("%d", this.player.getLevel()));
            value = value.replaceAll("<player.sp>", String.format("%d", this.player.getSkillPoints()));
            value = value.replaceAll("<player.status>", String.format(Strings.FORMAT_TIME, 
                TimeUnit.MICROSECONDS.toSeconds(this.player.getStatusDuration() / 60), 
                TimeUnit.MICROSECONDS.toSeconds(this.player.getStatusDuration() % 60)
            ));
        }
        // Target values
        if (this.target != null) {
            value = value.replaceAll("<target.nickname>", this.target.getNickname());
            value = value.replaceAll("<target.hp>", String.format("%.2f", Math.abs(this.getTargetHealthChanges())));
            value = value.replaceAll("<target.mp>", String.format("%.2f", Math.abs(this.getTargetManaChanges())));
            value = value.replaceAll("<target.xp>", String.format("%.2f", Math.abs(this.getTargetExperienceChanges())));
            value = value.replaceAll("<target.gold>", String.format("%.2f", Math.abs(this.getTargetGoldChanges())));
        }
        // Others values
        value = value.replaceAll("<value.int>", String.format("%d", this.getValue()));
        value = value.replaceAll("<value.double>", String.format("%.2f", this.getValue()));
        value = value.replaceAll("<details>", this.getDetails());
        return value;
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
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    
    public void addReturn(Return r) {
        if (!this.returns.contains(r)) {
            this.returns.add(r);
        }
    }
    
    public void removeReturn(Return r) {
        if (this.returns.contains(r)) {
            this.returns.remove(r);
        }
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Return> getReturns() {
        return returns;
    }

    public void setReturns(List<Return> returns) {
        this.returns = returns;
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
    }
    
    public void addPlayerHealthChanges(Double playerHealthChanges) {
        this.playerHealthChanges += playerHealthChanges;
    }

    public Double getPlayerManaChanges() {
        return playerManaChanges;
    }

    public void setPlayerManaChanges(Double playerManaChanges) {
        this.playerManaChanges = playerManaChanges;
    }
    
    public void addPlayerManaChanges(Double playerManaChanges) {
        this.playerManaChanges += playerManaChanges;
    }

    public Double getPlayerGoldChanges() {
        return playerGoldChanges;
    }

    public void setPlayerGoldChanges(Double playerGoldChanges) {
        this.playerGoldChanges = playerGoldChanges;
    }
    
    public void addPlayerGoldChanges(Double playerGoldChanges) {
        this.playerGoldChanges += playerGoldChanges;
    }

    public Double getPlayerExperienceChanges() {
        return playerExperienceChanges;
    }

    public void setPlayerExperienceChanges(Double playerExperienceChanges) {
        this.playerExperienceChanges = playerExperienceChanges;
    }
    
    public void addPlayerExperienceChanges(Double playerExperienceChanges) {
        this.playerExperienceChanges += playerExperienceChanges;
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
    }
    
    public void addTargetHealthChanges(Double enemyHealthChanges) {
        this.targetHealthChanges += enemyHealthChanges;
    }

    public Double getTargetManaChanges() {
        return targetManaChanges;
    }

    public void setTargetManaChanges(Double targetManaChanges) {
        this.targetManaChanges = targetManaChanges;
    }
    
    public void addTargetManaChanges(Double enemyManaChanges) {
        this.targetManaChanges += enemyManaChanges;
    }

    public Double getTargetGoldChanges() {
        return targetGoldChanges;
    }

    public void setTargetGoldChanges(Double targetGoldChanges) {
        this.targetGoldChanges = targetGoldChanges;
    }
    
    public void addTargetGoldChanges(Double enemyGoldChanges) {
        this.targetGoldChanges += enemyGoldChanges;
    }

    public Double getTargetExperienceChanges() {
        return targetExperienceChanges;
    }

    public void setTargetExperienceChanges(Double targetExperienceChanges) {
        this.targetExperienceChanges = targetExperienceChanges;
    }
    
    public void addTargetExperienceChanges(Double enemyExperienceChanges) {
        this.targetExperienceChanges += enemyExperienceChanges;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void addPlayerGoldChanges(int gold) {
        this.playerGoldChanges += gold;
    }
}
