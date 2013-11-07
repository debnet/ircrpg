package fr.debnet.ircrpg.interfaces;

import fr.debnet.ircrpg.enums.Model;
import java.io.Serializable;

/**
 * Interface for entities
 * @author Marc
 */
public interface IEntity extends Serializable {
    
    public Long getId();

    public void setId(Long id);

    public Integer getVersion();

    public void setVersion(Integer version);
    
    public Model getModel();
}
