package fr.debnet.ircrpg.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base entity for value mapping in inner map
 * @author Marc
 */
public abstract class MappedEntity {

    private Map<String, Object> map;
    private boolean enableMapping = true;
    
    /**
     * Constructor
     */
    public MappedEntity() {
        this.map = new HashMap<>();
    }
    
    /**
     * Enable mapping of the entity
     * @param enable True or false
     */
    public void setEnableMapping(boolean enable) {
        this.enableMapping = enable;
    }
    
    /**
     * Return the map representation of the entity
     * @return Map if enabled, null else
     */
    public Map<String, Object> toMap() {
        if (this.enableMapping) {
            this.updateMapping();
            return new HashMap<>(this.map);
        }
        return null;
    }
    
    /**
     * Set a value inner the entity map
     * @param key Key
     * @param value Value
     */
    protected synchronized void set(String key, Object value) {
        if (this.enableMapping) {
            if (this.map.containsKey(key)) this.map.remove(key);
            this.map.put(key, value);

            if (value instanceof MappedEntity) {
                MappedEntity entity = (MappedEntity) value;
                for (Map.Entry<String, Object> entry : entity.toMap().entrySet()) {
                    String subkey = String.format("%s.%s", key, entry.getKey());

                    if (this.map.containsKey(subkey)) this.map.remove(subkey);
                    this.map.put(subkey, entry.getValue());
                }
            }
        }
    }
    
    /**
     * Specific update mapping by entity
     */
    public abstract void updateMapping();
}
