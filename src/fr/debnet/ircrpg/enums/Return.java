/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.debnet.ircrpg.enums;

import fr.debnet.ircrpg.Strings;

/**
 *
 * @author Marc
 */
public enum Return {
    // General returns
    OK                          (0x00, Strings.RETURN_OK),
    PERSISTANCE_ERROR           (0x01, Strings.RETURN_PERSISTANCE_ERROR),
    UNKNOWN_ERROR               (0x02, Strings.RETURN_UNKNOWN_ERROR),
    // Update returns
    POISON_CURED                (0x10, Strings.RETURN_POISON_CURED),
    PARALYSIS_CURED             (0x11, Strings.RETURN_PARALYSIS_CURED),
    DEATH_CURED                 (0x12, Strings.RETURN_DEATH_CURED),
    KILLED_BY_POISON            (0x13, Strings.RETURN_KILLED_BY_POISON),
    WORKING_ENDED               (0x14, Strings.RETURN_WORKING_ENDED),
    RESTING_ENDED               (0x15, Strings.RETURN_RESTING_ENDED),
    TRAINING_ENDED              (0x16, Strings.RETURN_TRAINING_ENDED),
    WAITING_ENDED               (0x17, Strings.RETURN_WAITING_ENDED),
    LEVEL_UP                    (0x18, Strings.RETURN_LEVEL_UP),
    // General returns (player)
    UNKNOWN_PLAYER              (0x20, Strings.RETURN_UNKNOWN_PLAYER),
    PLAYER_OFFLINE              (0x21, Strings.RETURN_PLAYER_OFFLINE),
    PLAYER_DEAD                 (0x22, Strings.RETURN_PLAYER_DEAD),
    PLAYER_PARALYZED            (0x23, Strings.RETURN_PLAYER_PARALYZED),
    PLAYER_POISONED             (0x24, Strings.RETURN_PLAYER_POISONED),
    PLAYER_BUSY                 (0x25, Strings.RETURN_PLAYER_BUSY),
    PLAYER_KILLED               (0x26, Strings.RETURN_PLAYER_KILLED),
    // General returns (target)
    UNKNOWN_TARGET              (0x30, Strings.RETURN_UNKNOWN_TARGET),
    TARGET_OFFLINE              (0x31, Strings.RETURN_TARGET_OFFLINE),
    TARGET_DEAD                 (0x32, Strings.RETURN_TARGET_DEAD),
    TARGET_PARALYZED            (0x33, Strings.RETURN_TARGET_PARALYZED),
    TARGET_POISONED             (0x34, Strings.RETURN_TARGET_POISONED),
    TARGET_BUSY                 (0x35, Strings.RETURN_TARGET_BUSY),
    TARGET_KILLED               (0x36, Strings.RETURN_TARGET_KILLED),
    TARGET_CURED                (0x37, Strings.RETURN_TARGET_CURED),
    // Fighting returns
    SPELL_NOT_LEARNED           (0x40, Strings.RETURN_SPELL_NOT_LEARNED),
    NOT_ENOUGH_MANA             (0x41, Strings.RETURN_NOT_ENOUGH_MANA),
    NOT_SELF_ATTACK             (0x42, Strings.RETURN_NOT_SELF_ATTACK),
    NOT_SELF_SPELL              (0x43, Strings.RETURN_NOT_SELF_SPELL),
    ATTACK_FAILED               (0x44, Strings.RETURN_ATTACK_FAILED),
    DEFENSE_FAILED              (0x45, Strings.RETURN_DEFENSE_FAILED),
    MAGIC_FAILED                (0x46, Strings.RETURN_MAGIC_FAILED),
    ATTACK_SUCCEED              (0x47, Strings.RETURN_ATTACK_SUCCEED),
    DEFENSE_SUCCEED             (0x48, Strings.RETURN_DEFENSE_SUCCEED),
    MAGIC_SUCCEED               (0x49, Strings.RETURN_MAGIC_SUCCEED),
    MAGIC_DAMAGE_HEALTH         (0x4A, Strings.RETURN_MAGIC_DAMAGE_HEALTH),
    MAGIC_RESTORE_HEALTH        (0x4B, Strings.RETURN_MAGIC_RESTORE_HEALTH),
    NO_STRIKE_BACK              (0x4C, Strings.RETURN_NO_STRIKE_BACK),
    // Stealing returns
    NOT_SELF_THEFT              (0x50, Strings.RETURN_NOT_SELF_THEFT),
    THEFT_FAILED                (0x51, Strings.RETURN_THEFT_FAILED),
    THEFT_SUCCEED               (0x52, Strings.RETURN_THEFT_SUCCEED),
    // Drinking returns
    UNKNOWN_POTION              (0x60, Strings.RETURN_UNKNOWN_POTION),
    NOT_ENOUGH_HEALTH_POTIONS   (0x61, Strings.RETURN_NOT_ENOUGH_HEALTH_POTIONS),
    NOT_ENOUGH_MANA_POTIONS     (0x62, Strings.RETURN_NOT_ENOUGH_MANA_POTIONS),
    NOT_ENOUGH_REMEDY_POTIONS   (0x63, Strings.RETURN_NOT_ENOUGH_REMEDY_POTIONS),
    HEALTH_ALREADY_RESTORED     (0x64, Strings.RETURN_HEALTH_ALREADY_RESTORED),
    MANA_ALREADY_RESTORED       (0x65, Strings.RETURN_MANA_ALREADY_RESTORED),
    NO_NEGATIVE_STATUS          (0x66, Strings.RETURN_NO_NEGATIVE_STATUS),
    HEALTH_RESTORED             (0x67, Strings.RETURN_HEALTH_RESTORED),
    MANA_RESTORED               (0x68, Strings.RETURN_MANA_RESTORED),
    // Activity returns
    PLAYER_NOT_BUSY             (0x70, Strings.RETURN_PLAYER_NOT_BUSY),
    PLAYER_IS_WAITING           (0x71, Strings.RETURN_PLAYER_IS_WAITING),
    NOT_WORKED_ENOUGH           (0x72, Strings.RETURN_NOT_WORKED_ENOUGH),
    NOT_RESTED_ENOUGH           (0x73, Strings.RETURN_NOT_RESTED_ENOUGH),
    NOT_TRAINED_ENOUGH          (0x74, Strings.RETURN_NOT_TRAINED_ENOUGH),
    // Level up returns
    NOT_ENOUGH_SKILL_POINTS     (0x80, Strings.RETURN_NOT_ENOUGH_SKILL_POINTS),
    HEALTH_INCREASED            (0x81, Strings.RETURN_HEALTH_INCREASED),
    MANA_INCREASED              (0x82, Strings.RETURN_MANA_INCREASED),
    ATTACK_INCREASED            (0x83, Strings.RETURN_ATTACK_INCREASED),
    DEFENSE_INCREASED           (0x84, Strings.RETURN_DEFENSE_INCREASED),
    // Buy/sell returns
    UNKNOWN_ITEM                (0x90, Strings.RETURN_UNKNOWN_ITEM),
    ITEM_ALREADY_BOUGHT         (0x91, Strings.RETURN_ITEM_ALREADY_BOUGHT),
    ITEM_NOT_IN_INVENTORY       (0x92, Strings.RETURN_ITEM_NOT_IN_INVENTORY),
    ITEM_LEVEL_TOO_HIGH         (0x93, Strings.RETURN_ITEM_LEVEL_TOO_HIGH),
    ITEM_PRICE_TOO_HIGH         (0x94, Strings.RETURN_ITEM_PRICE_TOO_HIGH),
    ITEM_STOCK_EMPTY            (0x95, Strings.RETURN_ITEM_STOCK_EMPTY),
    ADMIN_ONLY_ITEM             (0x96, Strings.RETURN_ADMIN_ONLY_ITEM),
    ITEM_SUCCESSFULLY_BOUGHT    (0x97, Strings.RETURN_ITEM_SUCCESSFULLY_BOUGHT),
    ITEM_SUCCESSFULLY_SOLD      (0x98, Strings.RETURN_ITEM_SUCCESSFULLY_SOLD),
    POTION_PRICE_TOO_HIGH       (0x99, Strings.RETURN_POTION_PRICE_TOO_HIGH),
    POTION_SUCCESSFULLY_BOUGHT  (0x9A, Strings.RETURN_POTION_SUCCESSFULLY_BOUGHT),
    POTION_SUCCESSFULLY_SOLD    (0x9B, Strings.RETURN_POTION_SUCCESSFULLY_SOLD),
    // Learning returns
    UNKNOWN_SPELL               (0xA0, Strings.RETURN_UNKNOWN_SPELL),
    SPELL_ALREADY_LEARNED       (0xA1, Strings.RETURN_SPELL_ALREADY_LEARNED),
    SPELL_LEVEL_TOO_HIGH        (0xA2, Strings.RETURN_SPELL_LEVEL_TOO_HIGH),
    SPELL_PRICE_TOO_HIGH        (0xA3, Strings.RETURN_SPELL_PRICE_TOO_HIGH),
    ADMIN_ONLY_SPELL            (0xA4, Strings.RETURN_ADMIN_ONLY_SPELL),
    SPELL_SUCCESSFULLY_LEARNED  (0xA5, Strings.RETURN_SPELL_SUCCESSFULLY_LEARNED),
    // Logon returns
    USERNAME_ALREADY_TAKEN      (0xB0, Strings.RETURN_USERNAME_ALREADY_TAKEN),
    USERNAME_NOT_FOUND          (0xB1, Strings.RETURN_USERNAME_NOT_FOUND),
    WRONG_PASSWORD              (0xB2, Strings.RETURN_WRONG_PASSWORD),
    ALREADY_ONLINE              (0xB2, Strings.RETURN_ALREADY_ONLINE);
    
    private final int value;
    private final String text;

    private Return(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
}
