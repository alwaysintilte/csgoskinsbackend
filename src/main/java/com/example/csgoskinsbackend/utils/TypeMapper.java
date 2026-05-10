package com.example.csgoskinsbackend.utils;

import com.example.csgoskinsbackend.models.DTOs.GeneralItemDTO;
import com.example.csgoskinsbackend.models.DTOs.WeaponDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TypeMapper {
    public static String getTypeTable(String type){
        String typeTable = switch (type) {
            case "weapon" -> "weapons";
            case "sticker" -> "stickers";
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
        return typeTable;
    }
    public static GeneralItemDTO mapItemFromResultSet(ResultSet resultSet, String type) throws SQLException {
        return switch (type) {
            case "weapon" -> mapWeapon(resultSet);
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
}


