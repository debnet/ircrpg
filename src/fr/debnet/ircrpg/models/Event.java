/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Marc
 */
@Entity
public class Event implements Serializable, IEntity {
    
    @Id
    private Long id;
    @Version
    private Integer version;
    
    public Event() {
        
    }
    
    @Override
    public Model getModel() {
        return Model.EVENT;
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
}
