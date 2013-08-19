package fr.debnet.ircrpg.interfaces;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marc
 */
public abstract class MappedEntity {

    private Map<String, Object> map;
    private boolean enableMapping = true;
    
    public MappedEntity() {
        this.map = new HashMap<String, Object>();
    }
    
    public void setEnableMapping(boolean enable) {
        this.enableMapping = enable;
    }
    
    public Map<String, Object> toMap() {
        return new HashMap<String, Object>(this.map);
    }
    
    protected void set(String key, Object value) {
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
}
