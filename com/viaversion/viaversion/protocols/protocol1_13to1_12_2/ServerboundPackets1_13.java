/*
 * Decompiled with CFR 0.150.
 */
package com.viaversion.viaversion.protocols.protocol1_13to1_12_2;

import com.viaversion.viaversion.api.protocol.packet.ServerboundPacketType;

public enum ServerboundPackets1_13 implements ServerboundPacketType
{
    TELEPORT_CONFIRM,
    QUERY_BLOCK_NBT,
    CHAT_MESSAGE,
    CLIENT_STATUS,
    CLIENT_SETTINGS,
    TAB_COMPLETE,
    WINDOW_CONFIRMATION,
    CLICK_WINDOW_BUTTON,
    CLICK_WINDOW,
    CLOSE_WINDOW,
    PLUGIN_MESSAGE,
    EDIT_BOOK,
    ENTITY_NBT_REQUEST,
    INTERACT_ENTITY,
    KEEP_ALIVE,
    PLAYER_MOVEMENT,
    PLAYER_POSITION,
    PLAYER_POSITION_AND_ROTATION,
    PLAYER_ROTATION,
    VEHICLE_MOVE,
    STEER_BOAT,
    PICK_ITEM,
    CRAFT_RECIPE_REQUEST,
    PLAYER_ABILITIES,
    PLAYER_DIGGING,
    ENTITY_ACTION,
    STEER_VEHICLE,
    RECIPE_BOOK_DATA,
    RENAME_ITEM,
    RESOURCE_PACK_STATUS,
    ADVANCEMENT_TAB,
    SELECT_TRADE,
    SET_BEACON_EFFECT,
    HELD_ITEM_CHANGE,
    UPDATE_COMMAND_BLOCK,
    UPDATE_COMMAND_BLOCK_MINECART,
    CREATIVE_INVENTORY_ACTION,
    UPDATE_STRUCTURE_BLOCK,
    UPDATE_SIGN,
    ANIMATION,
    SPECTATE,
    PLAYER_BLOCK_PLACEMENT,
    USE_ITEM;


    @Override
    public int getId() {
        return this.ordinal();
    }

    @Override
    public String getName() {
        return this.name();
    }
}

