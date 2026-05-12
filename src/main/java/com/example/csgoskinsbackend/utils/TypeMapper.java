package com.example.csgoskinsbackend.utils;

import com.example.csgoskinsbackend.models.DTOs.items.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TypeMapper {
    public static String getTypeTable(String type){
        String typeTable = switch (type) {
            case "agent" -> "agents";
            case "collectible" -> "collectibles";
            case "graffiti" -> "graffiti";
            case "keychain" -> "keychains";
            case "key" -> "keys";
            case "music_kit" -> "music_kits";
            case "patch" -> "patches";
            case "weapon" -> "weapons";
            case "sticker" -> "stickers";
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
        return typeTable;
    }
    public static GeneralItemDTO mapItemFromResultSet(ResultSet resultSet, String type) throws SQLException {
        return switch (type) {
            case "agent" -> mapAgent(resultSet);
            case "collectible" -> mapCollectible(resultSet);
            case "graffiti" -> mapGraffiti(resultSet);
            case "keychain" -> mapKeychain(resultSet);
            case "key" -> mapKey(resultSet);
            case "music_kit" -> mapMusicKit(resultSet);
            case "patch" -> mapPatch(resultSet);
            case "weapon" -> mapWeapon(resultSet);
            case "sticker" -> mapSticker(resultSet);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
    private static WeaponDTO mapWeapon(ResultSet resultSet) throws java.sql.SQLException {
        WeaponDTO weaponDTO = new WeaponDTO();
        weaponDTO.setId(resultSet.getInt("id"));
        weaponDTO.setName(resultSet.getString("name"));
        weaponDTO.setDescription(resultSet.getString("description"));
        weaponDTO.setType(resultSet.getString("type"));
        weaponDTO.setImage(resultSet.getString("image"));
        weaponDTO.setRarity(resultSet.getString("rarity"));
        weaponDTO.setWeapon(resultSet.getString("weapon"));
        weaponDTO.setCategory(resultSet.getString("category"));
        weaponDTO.setMinFloat(resultSet.getDouble("min_float"));
        weaponDTO.setMaxFloat(resultSet.getDouble("max_float"));
        weaponDTO.setStattrak(resultSet.getBoolean("stattrak"));
        weaponDTO.setSouvenir(resultSet.getBoolean("souvenir"));
        weaponDTO.setPaintIndex(resultSet.getString("paint_index"));
        String[] array = (String[]) resultSet.getArray("wears").getArray();
        weaponDTO.setWears(Arrays.asList(array));
        return weaponDTO;
    }
    private static AgentDTO mapAgent(ResultSet resultSet) throws java.sql.SQLException {
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setId(resultSet.getInt("id"));
        agentDTO.setName(resultSet.getString("name"));
        agentDTO.setDescription(resultSet.getString("description"));
        agentDTO.setType(resultSet.getString("type"));
        agentDTO.setImage(resultSet.getString("image"));
        agentDTO.setRarity(resultSet.getString("rarity"));
        agentDTO.setDefIndex(resultSet.getInt("def_index"));
        agentDTO.setTeam(resultSet.getString("team"));
        return agentDTO;
    }
    private static CollectibleDTO mapCollectible(ResultSet resultSet) throws java.sql.SQLException {
        CollectibleDTO collectibleDTO = new CollectibleDTO();
        collectibleDTO.setId(resultSet.getInt("id"));
        collectibleDTO.setName(resultSet.getString("name"));
        collectibleDTO.setDescription(resultSet.getString("description"));
        collectibleDTO.setType(resultSet.getString("type"));
        collectibleDTO.setImage(resultSet.getString("image"));
        collectibleDTO.setRarity(resultSet.getString("rarity"));
        collectibleDTO.setDefIndex(resultSet.getInt("def_index"));
        collectibleDTO.setCollectibleType(resultSet.getString("collectible_type"));
        collectibleDTO.setGenuine(resultSet.getBoolean("is_genuine"));
        return collectibleDTO;
    }
    private static GraffitiDTO mapGraffiti(ResultSet resultSet) throws java.sql.SQLException {
        GraffitiDTO graffitiDTO = new GraffitiDTO();
        graffitiDTO.setId(resultSet.getInt("id"));
        graffitiDTO.setName(resultSet.getString("name"));
        graffitiDTO.setDescription(resultSet.getString("description"));
        graffitiDTO.setType(resultSet.getString("type"));
        graffitiDTO.setImage(resultSet.getString("image"));
        graffitiDTO.setRarity(resultSet.getString("rarity"));
        graffitiDTO.setDefIndex(resultSet.getInt("def_index"));
        return graffitiDTO;
    }
    private static KeychainDTO mapKeychain(ResultSet resultSet) throws java.sql.SQLException {
        KeychainDTO keychainDTO = new KeychainDTO();
        keychainDTO.setId(resultSet.getInt("id"));
        keychainDTO.setName(resultSet.getString("name"));
        keychainDTO.setDescription(resultSet.getString("description"));
        keychainDTO.setType(resultSet.getString("type"));
        keychainDTO.setImage(resultSet.getString("image"));
        keychainDTO.setRarity(resultSet.getString("rarity"));
        keychainDTO.setDefIndex(resultSet.getInt("def_index"));
        return keychainDTO;
    }
    private static KeyDTO mapKey(ResultSet resultSet) throws java.sql.SQLException {
        KeyDTO keyDTO = new KeyDTO();
        keyDTO.setId(resultSet.getInt("id"));
        keyDTO.setName(resultSet.getString("name"));
        keyDTO.setDescription(resultSet.getString("description"));
        keyDTO.setType(resultSet.getString("type"));
        keyDTO.setImage(resultSet.getString("image"));
        keyDTO.setRarity(resultSet.getString("rarity"));
        keyDTO.setDefIndex(resultSet.getInt("def_index"));
        keyDTO.setMarketable(resultSet.getBoolean("marketable"));
        return keyDTO;
    }
    private static MusicKitDTO mapMusicKit(ResultSet resultSet) throws java.sql.SQLException {
        MusicKitDTO musicKitDTO = new MusicKitDTO();
        musicKitDTO.setId(resultSet.getInt("id"));
        musicKitDTO.setName(resultSet.getString("name"));
        musicKitDTO.setDescription(resultSet.getString("description"));
        musicKitDTO.setType(resultSet.getString("type"));
        musicKitDTO.setImage(resultSet.getString("image"));
        musicKitDTO.setRarity(resultSet.getString("rarity"));
        musicKitDTO.setDefIndex(resultSet.getInt("def_index"));
        musicKitDTO.setExclusive(resultSet.getBoolean("exclusive"));
        return musicKitDTO;
    }
    private static PatchDTO mapPatch(ResultSet resultSet) throws java.sql.SQLException {
        PatchDTO patchDTO = new PatchDTO();
        patchDTO.setId(resultSet.getInt("id"));
        patchDTO.setName(resultSet.getString("name"));
        patchDTO.setDescription(resultSet.getString("description"));
        patchDTO.setType(resultSet.getString("type"));
        patchDTO.setImage(resultSet.getString("image"));
        patchDTO.setRarity(resultSet.getString("rarity"));
        patchDTO.setDefIndex(resultSet.getInt("def_index"));
        return patchDTO;
    }
    private static StickerDTO mapSticker(ResultSet resultSet) throws java.sql.SQLException {
        StickerDTO stickerDTO = new StickerDTO();
        stickerDTO.setId(resultSet.getInt("id"));
        stickerDTO.setName(resultSet.getString("name"));
        stickerDTO.setDescription(resultSet.getString("description"));
        stickerDTO.setType(resultSet.getString("type"));
        stickerDTO.setImage(resultSet.getString("image"));
        stickerDTO.setRarity(resultSet.getString("rarity"));
        stickerDTO.setDefIndex(resultSet.getInt("def_index"));
        stickerDTO.setStickerType(resultSet.getString("sticker_type"));
        stickerDTO.setEffect(resultSet.getString("effect"));
        stickerDTO.setTournament(resultSet.getString("tournament"));
        stickerDTO.setTeam(resultSet.getString("team"));
        stickerDTO.setPlayer(resultSet.getString("player"));
        return stickerDTO;
    }
}