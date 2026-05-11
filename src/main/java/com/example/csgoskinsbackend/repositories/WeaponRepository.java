package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.models.DTOs.items.WeaponDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WeaponRepository {
    private final JdbcTemplate jdbcTemplate;
    public WeaponRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
//    public List<GeneralItemDTO> getAllWeapons(){
//        List<GeneralItemDTO> weapons = this.jdbcTemplate.query(
//                "SELECT general_items.id, general_items.name, general_items.description, general_items.image, general_items.rarity, weapons.weapon, weapons.category, weapons.min_float, weapons.max_float, weapons.stattrak, weapons.souvenir, weapons.paint_index, weapons.wears FROM general_items JOIN weapons ON general_items.id = weapons.id LIMIT 100",
//                (resultSet, rowNum) -> {
//                    WeaponDTO weaponDTO = new WeaponDTO();
//                    weaponDTO.setId(resultSet.getInt("id"));
//                    weaponDTO.setName(resultSet.getString("name"));
//                    weaponDTO.setDescription(resultSet.getString("description"));
//                    weaponDTO.setImage(resultSet.getString("image"));
//                    weaponDTO.setRarity(resultSet.getString("rarity"));
//                    weaponDTO.setWeapon(resultSet.getString("weapon"));
//                    weaponDTO.setCategory(resultSet.getString("category"));
//                    weaponDTO.setMinFloat(resultSet.getDouble("min_float"));
//                    weaponDTO.setMaxFloat(resultSet.getDouble("max_float"));
//                    weaponDTO.setStattrak(resultSet.getBoolean("stattrak"));
//                    weaponDTO.setSouvenir(resultSet.getBoolean("souvenir"));
//                    weaponDTO.setPaintIndex(resultSet.getString("paint_index"));
//                    String[] array = (String[]) resultSet.getArray("wears").getArray();
//                    weaponDTO.setWears(Arrays.asList(array));
//                    return weaponDTO;
//                }
//        );
//        return weapons;
//    }
    public Map<Integer, GeneralItemDTO> getWeaponsById(List<Integer> ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        return this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, weapons.weapon, weapons.category, weapons.min_float, weapons.max_float, weapons.stattrak, weapons.souvenir, weapons.paint_index, weapons.wears " +
                        "FROM general_items JOIN weapons ON general_items.id = weapons.id " +
                        "WHERE general_items.id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, GeneralItemDTO> map = new HashMap<>();
                    while (resultSet.next()) {
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
                        map.put(weaponDTO.getId(), weaponDTO);
                    }
                    return map;
                }
        );
    }
//    public GeneralItemDTO getWeaponById(Integer id){
//        WeaponDTO weaponDTO = jdbcTemplate.queryForObject(
//                "SELECT general_items.id, general_items.name, general_items.description, general_items.image, general_items.rarity, weapons.weapon, weapons.category, weapons.min_float, weapons.max_float, weapons.stattrak, weapons.souvenir, weapons.paint_index, weapons.wears FROM general_items JOIN weapons ON general_items.id = weapons.id WHERE general_items.id = ?",
//                (resultSet, rowNum) -> {
//                    WeaponDTO newWeaponDTO = new WeaponDTO();
//                    newWeaponDTO.setId(resultSet.getInt("id"));
//                    newWeaponDTO.setName(resultSet.getString("name"));
//                    newWeaponDTO.setDescription(resultSet.getString("description"));
//                    newWeaponDTO.setImage(resultSet.getString("image"));
//                    newWeaponDTO.setRarity(resultSet.getString("rarity"));
//                    newWeaponDTO.setWeapon(resultSet.getString("weapon"));
//                    newWeaponDTO.setCategory(resultSet.getString("category"));
//                    newWeaponDTO.setMinFloat(resultSet.getDouble("min_float"));
//                    newWeaponDTO.setMaxFloat(resultSet.getDouble("max_float"));
//                    newWeaponDTO.setStattrak(resultSet.getBoolean("stattrak"));
//                    newWeaponDTO.setSouvenir(resultSet.getBoolean("souvenir"));
//                    newWeaponDTO.setPaintIndex(resultSet.getString("paint_index"));
//                    String[] array = (String[]) resultSet.getArray("wears").getArray();
//                    newWeaponDTO.setWears(Arrays.asList(array));
//                    return newWeaponDTO;
//                },
//                id
//        );
//        return weaponDTO;
//    }
//    public void addWeapon(WeaponDTO weaponDTO){
//        jdbcTemplate.update(connection -> {
//            PreparedStatement preparedStatement = connection.prepareStatement(
//                    "INSERT INTO weapons (id, weapon, category, min_float, max_float, stattrak, souvenir, paint_index, wears) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
//                    new String[]{"id"}
//            );
//            preparedStatement.setInt(1, weaponDTO.getId());
//            preparedStatement.setString(2, weaponDTO.getWeapon());
//            preparedStatement.setString(3, weaponDTO.getCategory());
//            preparedStatement.setDouble(4, weaponDTO.getMinFloat());
//            preparedStatement.setDouble(5, weaponDTO.getMaxFloat());
//            preparedStatement.setBoolean(6, weaponDTO.getStattrak());
//            preparedStatement.setBoolean(7, weaponDTO.getSouvenir());
//            preparedStatement.setString(8, weaponDTO.getPaintIndex());
//            Array sqlArray = connection.createArrayOf("text", weaponDTO.getWears().toArray(new String[0]));
//            preparedStatement.setArray(9, sqlArray);
//            return preparedStatement;
//        });
//    }
}
