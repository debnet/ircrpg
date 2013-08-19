package fr.debnet.ircrpg.interfaces;

import fr.debnet.ircrpg.enums.Model;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marc
 */
public abstract class MappedEntity implements IEntity {

    private Map<String, Object> map;
    
    @Override
    public abstract Long getId();

    @Override
    public abstract void setId(Long id);

    @Override
    public abstract Integer getVersion();

    @Override
    public abstract void setVersion(Integer version);

    @Override
    public abstract Model getModel();
    
    protected abstract void setDefaultValues();
    
    public MappedEntity() {
        this.map = new HashMap<String, Object>();
        this.setDefaultValues();
    }
    
    public Map<String, Object> toMap() {
        return new HashMap<String, Object>(this.map);
    }
    
    protected void set(String key, Object value) {
        if (this.map.containsKey(key)) this.map.remove(key);
        this.map.put(key, value);
    }
}
