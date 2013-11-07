package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.commons.Strings;
import fr.debnet.ircrpg.interfaces.IEnum;

/**
 * Returns
 * @author Marc
 */
public enum Return implements IEnum {
    
    // General returns
    OK                          (0x00, Strings.RETURN_OK),
    PERSISTANCE_ERROR           (0x01, Strings.RETURN_PERSISTANCE_ERROR),
    UNKNOWN_ERROR               (0x02, Strings.RETURN_UNKNOWN_ERROR),
    UNKNOWN_COMMAND             (0x03, Strings.RETURN_UNKNOWN_COMMAND),
    ADMIN_COMMAND_SUCCEED       (0x04, Strings.RETURN_ADMIN_COMMAND_SUCCEED),
    ADMIN_COMMAND_FAILED        (0x05, Strings.RETURN_ADMIN_COMMAND_FAILED),
    ACTION_TOO_FAST             (0x06, Strings.RETURN_ACTION_TOO_FAST),
    // General returns (player)
    UNKNOWN_PLAYER              (0x10, Strings.RETURN_UNKNOWN_PLAYER),
    PLAYER_OFFLINE              (0x11, Strings.RETURN_OFFLINE_PLAYER),
    PLAYER_DEAD                 (0x12, Strings.RETURN_PLAYER_DEAD),
    PLAYER_PARALYZED            (0x13, Strings.RETURN_PLAYER_PARALYZED),
    PLAYER_POISONED             (0x14, Strings.RETURN_PLAYER_POISONED),
    PLAYER_BUSY                 (0x15, Strings.RETURN_PLAYER_BUSY),
    PLAYER_KILLED               (0x16, Strings.RETURN_PLAYER_KILLED),
    PLAYER_LEVEL_UP             (0x17, Strings.RETURN_PLAYER_LEVEL_UP),
    PLAYER_POISON_CURED         (0x18, Strings.RETURN_PLAYER_POISON_CURED),
    PLAYER_PARALYSIS_CURED      (0x19, Strings.RETURN_PLAYER_PARALYSIS_CURED),
    PLAYER_DEATH_CURED          (0x1A, Strings.RETURN_PLAYER_DEATH_CURED),
    PLAYER_KILLED_BY_POISON     (0x1B, Strings.RETURN_PLAYER_KILLED_BY_POISON),
    PLAYER_WAITING_ENDED        (0x1C, Strings.RETURN_PLAYER_WAITING_ENDED),
    PLAYER_WORKING_ENDED        (0x1D, Strings.RETURN_PLAYER_WORKING_ENDED),
    PLAYER_TRAINING_ENDED       (0x1E, Strings.RETURN_PLAYER_TRAINING_ENDED),
    PLAYER_RESTING_ENDED        (0x1F, Strings.RETURN_PLAYER_RESTING_ENDED),
    PLAYER_PRAYING_ENDED        (0x20, Strings.RETURN_PLAYER_PRAYING_ENDED),
    // General returns (target)
    UNKNOWN_TARGET              (0x30, Strings.RETURN_UNKNOWN_PLAYER),
    TARGET_OFFLINE              (0x31, Strings.RETURN_OFFLINE_PLAYER),
    TARGET_DEAD                 (0x32, Strings.RETURN_TARGET_DEAD),
    TARGET_PARALYZED            (0x33, Strings.RETURN_TARGET_PARALYZED),
    TARGET_POISONED             (0x34, Strings.RETURN_TARGET_POISONED),
    TARGET_BUSY                 (0x35, Strings.RETURN_TARGET_BUSY),
    TARGET_KILLED               (0x36, Strings.RETURN_TARGET_KILLED),
    TARGET_LEVEL_UP             (0x37, Strings.RETURN_TARGET_LEVEL_UP),
    TARGET_POISON_CURED         (0x38, Strings.RETURN_TARGET_POISON_CURED),
    TARGET_PARALYSIS_CURED      (0x39, Strings.RETURN_TARGET_PARALYSIS_CURED),
    TARGET_DEATH_CURED          (0x3A, Strings.RETURN_TARGET_DEATH_CURED),
    TARGET_KILLED_BY_POISON     (0x3B, Strings.RETURN_TARGET_KILLED_BY_POISON),
    TARGET_WAITING_ENDED        (0x3C, Strings.RETURN_TARGET_WAITING_ENDED),
    TARGET_WORKING_ENDED        (0x3D, Strings.RETURN_TARGET_WORKING_ENDED),
    TARGET_TRAINING_ENDED       (0x3E, Strings.RETURN_TARGET_TRAINING_ENDED),
    TARGET_RESTING_ENDED        (0x3F, Strings.RETURN_TARGET_RESTING_ENDED),
    TARGET_PRAYING_ENDED        (0x40, Strings.RETURN_TARGET_PRAYING_ENDED),
    // Fighting returns
    SPELL_NOT_LEARNED           (0x50, Strings.RETURN_SPELL_NOT_LEARNED),
    NOT_ENOUGH_MANA             (0x51, Strings.RETURN_NOT_ENOUGH_MANA),
    NOT_SELF_ATTACK             (0x52, Strings.RETURN_NOT_SELF_ATTACK),
    NOT_SELF_SPELL              (0x53, Strings.RETURN_NOT_SELF_SPELL),
    ATTACK_FAILED               (0x54, Strings.RETURN_ATTACK_FAILED),
    DEFENSE_FAILED              (0x55, Strings.RETURN_DEFENSE_FAILED),
    MAGIC_FAILED                (0x56, Strings.RETURN_MAGIC_FAILED),
    ATTACK_SUCCEED              (0x57, Strings.RETURN_ATTACK_SUCCEED),
    DEFENSE_SUCCEED             (0x58, Strings.RETURN_DEFENSE_SUCCEED),
    MAGIC_SUCCEED               (0x59, Strings.RETURN_MAGIC_SUCCEED),
    MAGIC_DAMAGE_HEALTH         (0x5A, Strings.RETURN_MAGIC_DAMAGE_HEALTH),
    MAGIC_RESTORE_HEALTH        (0x5B, Strings.RETURN_MAGIC_RESTORE_HEALTH),
    NO_STRIKE_BACK              (0x5C, Strings.RETURN_NO_STRIKE_BACK),
    // Stealing returns
    NOT_SELF_THEFT              (0x60, Strings.RETURN_NOT_SELF_THEFT),
    THEFT_FAILED                (0x61, Strings.RETURN_THEFT_FAILED),
    THEFT_SUCCEED               (0x62, Strings.RETURN_THEFT_SUCCEED),
    // Drinking returns
    UNKNOWN_POTION              (0x70, Strings.RETURN_UNKNOWN_POTION),
    NOT_ENOUGH_HEALTH_POTIONS   (0x71, Strings.RETURN_NOT_ENOUGH_HEALTH_POTIONS),
    NOT_ENOUGH_MANA_POTIONS     (0x72, Strings.RETURN_NOT_ENOUGH_MANA_POTIONS),
    NOT_ENOUGH_REMEDY_POTIONS   (0x73, Strings.RETURN_NOT_ENOUGH_REMEDY_POTIONS),
    HEALTH_ALREADY_RESTORED     (0x74, Strings.RETURN_HEALTH_ALREADY_RESTORED),
    MANA_ALREADY_RESTORED       (0x75, Strings.RETURN_MANA_ALREADY_RESTORED),
    NO_NEGATIVE_STATUS          (0x76, Strings.RETURN_NO_NEGATIVE_STATUS),
    HEALTH_RESTORED             (0x77, Strings.RETURN_HEALTH_RESTORED),
    MANA_RESTORED               (0x78, Strings.RETURN_MANA_RESTORED),
    // Activity returns
    PLAYER_NOT_BUSY             (0x80, Strings.RETURN_PLAYER_NOT_BUSY),
    PLAYER_IS_WAITING           (0x81, Strings.RETURN_PLAYER_IS_WAITING),
    NOT_WORKED_ENOUGH           (0x82, Strings.RETURN_NOT_WORKED_ENOUGH),
    NOT_RESTED_ENOUGH           (0x83, Strings.RETURN_NOT_RESTED_ENOUGH),
    NOT_TRAINED_ENOUGH          (0x84, Strings.RETURN_NOT_TRAINED_ENOUGH),
    START_WORKING               (0x85, Strings.RETURN_START_WORKING),
    START_RESTING               (0x86, Strings.RETURN_START_RESTING),
    START_TRAINING              (0x87, Strings.RETURN_START_TRAINING),
    // Level up returns
    UNKNOWN_SKILL               (0x90, Strings.RETURN_UNKNOWN_SKILL),
    NOT_ENOUGH_SKILL_POINTS     (0x91, Strings.RETURN_NOT_ENOUGH_SKILL_POINTS),
    HEALTH_INCREASED            (0x92, Strings.RETURN_HEALTH_INCREASED),
    MANA_INCREASED              (0x93, Strings.RETURN_MANA_INCREASED),
    ATTACK_INCREASED            (0x94, Strings.RETURN_ATTACK_INCREASED),
    DEFENSE_INCREASED           (0x95, Strings.RETURN_DEFENSE_INCREASED),
    // Buy/sell returns
    UNKNOWN_ITEM                (0xA0, Strings.RETURN_UNKNOWN_ITEM),
    ITEM_ALREADY_BOUGHT         (0xA1, Strings.RETURN_ITEM_ALREADY_BOUGHT),
    ITEM_NOT_IN_INVENTORY       (0xA2, Strings.RETURN_ITEM_NOT_IN_INVENTORY),
    ITEM_LEVEL_TOO_HIGH         (0xA3, Strings.RETURN_ITEM_LEVEL_TOO_HIGH),
    ITEM_PRICE_TOO_HIGH         (0xA4, Strings.RETURN_ITEM_PRICE_TOO_HIGH),
    ITEM_STOCK_EMPTY            (0xA5, Strings.RETURN_ITEM_STOCK_EMPTY),
    ADMIN_ONLY_ITEM             (0xA6, Strings.RETURN_ADMIN_ONLY_ITEM),
    TYPE_ALREADY_EQUIPPED       (0xA7, Strings.RETURN_TYPE_ALREADY_EQUIPPED),
    ITEM_SUCCESSFULLY_BOUGHT    (0xA8, Strings.RETURN_ITEM_SUCCESSFULLY_BOUGHT),
    ITEM_SUCCESSFULLY_SOLD      (0xA9, Strings.RETURN_ITEM_SUCCESSFULLY_SOLD),
    POTION_PRICE_TOO_HIGH       (0xAA, Strings.RETURN_POTION_PRICE_TOO_HIGH),
    POTION_SUCCESSFULLY_BOUGHT  (0xAB, Strings.RETURN_POTION_SUCCESSFULLY_BOUGHT),
    POTION_SUCCESSFULLY_SOLD    (0xAC, Strings.RETURN_POTION_SUCCESSFULLY_SOLD),
    // Learning returns
    UNKNOWN_SPELL               (0xB0, Strings.RETURN_UNKNOWN_SPELL),
    SPELL_ALREADY_LEARNED       (0xB1, Strings.RETURN_SPELL_ALREADY_LEARNED),
    SPELL_LEVEL_TOO_HIGH        (0xB2, Strings.RETURN_SPELL_LEVEL_TOO_HIGH),
    SPELL_PRICE_TOO_HIGH        (0xB3, Strings.RETURN_SPELL_PRICE_TOO_HIGH),
    ADMIN_ONLY_SPELL            (0xB4, Strings.RETURN_ADMIN_ONLY_SPELL),
    SPELL_SUCCESSFULLY_LEARNED  (0xB5, Strings.RETURN_SPELL_SUCCESSFULLY_LEARNED),
    // Logon returns
    USERNAME_ALREADY_TAKEN      (0xC0, Strings.RETURN_USERNAME_ALREADY_TAKEN),
    USERNAME_NOT_FOUND          (0xC1, Strings.RETURN_USERNAME_NOT_FOUND),
    NICKNAME_IN_USE             (0xC2, Strings.RETURN_NICKNAME_IN_USE),
    WRONG_PASSWORD              (0xC3, Strings.RETURN_WRONG_PASSWORD),
    ALREADY_ONLINE              (0xC4, Strings.RETURN_ALREADY_ONLINE),
    NOT_ONLINE                  (0xC5, Strings.RETURN_NOT_ONLINE),
    REGISTER_SUCCEED            (0xC6, Strings.RETURN_REGISTER_SUCCEED),
    LOGIN_SUCCEED               (0xC7, Strings.RETURN_LOGIN_SUCCEED),
    LOGOUT_SUCCEED              (0xC8, Strings.RETURN_LOGOUT_SUCCEED),
    PASSWORD_CHANGED            (0xC9, Strings.RETURN_PASSWORD_CHANGED);
    
    private final int value;
    private final String text;

    private Return(int value, String text) {
        this.value = value;
        this.text = text;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
    
    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
