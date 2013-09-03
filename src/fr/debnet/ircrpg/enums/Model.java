package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.interfaces.IEnum;
import fr.debnet.ircrpg.models.Event;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Item;
import fr.debnet.ircrpg.models.Spell;

/**
 *
 * @author Marc
 */
public enum Model {
    
    NONE        (null),
    PLAYER      (Player.class),
    ITEM        (Item.class),
    SPELL       (Spell.class),
    EVENT       (Event.class),
    RESULT      (Event.class);

    private final Class value;

    private Model(Class value) {
        this.value = value;
    }
    
    public static Model from(String name) {
        for (Model model : Model.values()) {
            if (model.value == null) continue;
            if (model.value.getSimpleName().equalsIgnoreCase(name)) {
                return model;
            }
        }
        return Model.NONE;
    }

    @Override
    public String toString() {
        return this.value.getName();
    }
}
