package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class WeaponRepository {
    private final JdbcTemplate jdbcTemplate;
    public WeaponRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Scheduled(fixedRate = 6000)
    public List<WeaponDTO> getAllWeapons(){
        List<WeaponDTO> weapons = this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.image, weapons.weapon, weapons.category, weapons.min_float, weapons.max_float, weapons.rarity, weapons.stattrak, weapons.souvenir, weapons.paint_index, weapons.wears FROM general_items JOIN weapons ON general_items.id = weapons.id",
                (resultSet, rowNum) -> {
                    WeaponDTO weaponDTO = new WeaponDTO();
                    weaponDTO.setId(resultSet.getInt("id"));
                    weaponDTO.setName(resultSet.getString("name"));
                    weaponDTO.setDescription(resultSet.getString("description"));
                    weaponDTO.setImage(resultSet.getString("image"));
                    weaponDTO.setWeapon(resultSet.getString("weapon"));
                    weaponDTO.setCategory(resultSet.getString("category"));
                    weaponDTO.setMinFloat(resultSet.getDouble("min_float"));
                    weaponDTO.setMaxFloat(resultSet.getDouble("max_float"));
                    weaponDTO.setRarity(resultSet.getString("rarity"));
                    weaponDTO.setStattrak(resultSet.getBoolean("stattrak"));
                    weaponDTO.setSouvenir(resultSet.getBoolean("souvenir"));
                    weaponDTO.setPaintIndex(resultSet.getString("paint_index"));
                    String[] array = (String[]) resultSet.getArray("wears").getArray();
                    weaponDTO.setWears(Arrays.asList(array));
                    return weaponDTO;
                }
        );
        return weapons;
    }
    public void addWeapon(WeaponDTO weaponDTO){
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO weapons (id, weapon, category, min_float, max_float, rarity, stattrak, souvenir, paint_index, wears) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    new String[]{"id"}
            );
            preparedStatement.setInt(1, weaponDTO.getId());
            preparedStatement.setString(2, weaponDTO.getWeapon());
            preparedStatement.setString(3, weaponDTO.getCategory());
            preparedStatement.setDouble(4, weaponDTO.getMinFloat());
            preparedStatement.setDouble(5, weaponDTO.getMaxFloat());
            preparedStatement.setString(6, weaponDTO.getRarity());
            preparedStatement.setBoolean(7, weaponDTO.getStattrak());
            preparedStatement.setBoolean(8, weaponDTO.getSouvenir());
            preparedStatement.setString(9, weaponDTO.getPaintIndex());
            Array sqlArray = connection.createArrayOf("text", weaponDTO.getWears().toArray(new String[0]));
            preparedStatement.setArray(9, sqlArray);
            return preparedStatement;
        });
    }
}
