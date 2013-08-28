package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;
import fr.debnet.ircrpg.interfaces.IEntity;
import fr.debnet.ircrpg.interfaces.MappedEntity;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
