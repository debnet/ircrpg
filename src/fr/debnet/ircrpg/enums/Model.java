package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.models.Event;
import fr.debnet.ircrpg.models.Player;
import fr.debnet.ircrpg.models.Item;
import fr.debnet.ircrpg.models.Spell;

/**
 *
 * @author Marc
 */
public enum Model {
    PLAYER      (Player.class),
    ITEM        (Item.class),
    SPELL       (Spell.class),
    EVENT       (Event.class),
    RESULT      (Event.class);

    private final Class value;

    private Model(Class value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.getName();
    }
}
