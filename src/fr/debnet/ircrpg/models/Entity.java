/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.models;

import fr.debnet.ircrpg.enums.Model;
import java.io.Serializable;
import java.util.Map;
import net.sf.cglib.beans.BeanMap;

/**
 *
 * @author Marc
 */
public abstract class Entity implements Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract Integer getVersion();

    public abstract void setVersion(Integer version);
    
    public abstract Model getModel();
    
    public Map<String, Object> toMap() {
        return BeanMap.create(this);
    }
}
