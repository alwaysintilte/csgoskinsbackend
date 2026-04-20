package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ItemRepository {
    private final JdbcTemplate jdbcTemplate;
    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Map<Integer, List<CollectionDTO>> getAllCollections(List<Integer> ids){
        if (ids.isEmpty()){
            return new HashMap<>();
        }
        Map<Integer, List<CollectionDTO>> collectionMap = this.jdbcTemplate.query(
                "SELECT item_collections.item_id, collections.id, collections.name, collections.image, collections.date_added FROM item_collections JOIN collections ON item_collections.collection_id = collections.id WHERE item_collections.item_id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, List<CollectionDTO>> map = new HashMap<>();
                    while (resultSet.next()){
                        Integer itemId = resultSet.getInt("item_id");
                        if(map.containsKey(itemId)){
                            map.get(itemId).add(new CollectionDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getDate("date_added").toLocalDate()));
                        }
                        else {
                            List<CollectionDTO> collectionDTOS = new ArrayList<>();
                            collectionDTOS.add(new CollectionDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getDate("date_added").toLocalDate()));
                            map.put(itemId, collectionDTOS);
                        }
                    }
                    return map;
                }
        );
        return collectionMap;
    }
    public Map<Integer, List<CrateDTO>> getAllCrates(List<Integer> ids){
        if (ids.isEmpty()){
            return new HashMap<>();
        }
        Map<Integer, List<CrateDTO>> collectionMap = this.jdbcTemplate.query(
                "SELECT item_crates.item_id, crates.id, crates.name, crates.image, crates.date_added FROM item_crates JOIN crates ON item_crates.crate_id = crates.id WHERE item_crates.item_id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, List<CrateDTO>> map = new HashMap<>();
                    while (resultSet.next()){
                        Integer itemId = resultSet.getInt("item_id");
                        if(map.containsKey(itemId)){
                            map.get(itemId).add(new CrateDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getDate("date_added").toLocalDate()));
                        }
                        else {
                            List<CrateDTO> crateDTOS = new ArrayList<>();
                            crateDTOS.add(new CrateDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getDate("date_added").toLocalDate()));
                            map.put(itemId, crateDTOS);
                        }
                    }
                    return map;
                }
        );
        return collectionMap;
    }
    public Map<Integer, List<MarketLinkDTO>> getAllMarketLinks(List<Integer> ids){
        if (ids.isEmpty()){
            return new HashMap<>();
        }
        Map<Integer, List<MarketLinkDTO>> collectionMap = this.jdbcTemplate.query(
                "SELECT market_links.item_id, market_links.wear, market_links.stattrak, market_links.souvenir, market_links.link FROM market_links WHERE market_links.item_id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, List<MarketLinkDTO>> map = new HashMap<>();
                    while (resultSet.next()){
                        Integer itemId = resultSet.getInt("item_id");
                        if(map.containsKey(itemId)){
                            map.get(itemId).add(new MarketLinkDTO(resultSet.getInt("item_id"), resultSet.getString("wear"), resultSet.getBoolean("stattrak"), resultSet.getBoolean("souvenir"), resultSet.getString("link")));
                        }
                        else {
                            List<MarketLinkDTO> marketDTOS = new ArrayList<>();
                            marketDTOS.add(new MarketLinkDTO(resultSet.getInt("item_id"), resultSet.getString("wear"), resultSet.getBoolean("stattrak"), resultSet.getBoolean("souvenir"), resultSet.getString("link")));
                            map.put(itemId, marketDTOS);
                        }
                    }
                    return map;
                }
        );
        return collectionMap;
    }
    public Integer addCollection(CollectionDTO collectionDTO){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO collections (name, image, date_added) VALUES (?, ?, ?)",
                    new String[]{"id"}
            );
            preparedStatement.setString(1, collectionDTO.getName());
            preparedStatement.setString(2, collectionDTO.getImage());
            preparedStatement.setDate(3, Date.valueOf(collectionDTO.getDateAdded()));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    public Integer addCrate(CrateDTO crateDTO){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO crates (name, image, date_added) VALUES (?, ?, ?)",
                    new String[]{"id"}
            );
            preparedStatement.setString(1, crateDTO.getName());
            preparedStatement.setString(2, crateDTO.getImage());
            preparedStatement.setDate(3, Date.valueOf(crateDTO.getDateAdded()));
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    public Integer addItem(GeneralItemDTO generalItemDTO){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO general_items (name, description, type, image) VALUES (?, ?, ?, ?)",
                    new String[]{"id"}
            );
            preparedStatement.setString(1, generalItemDTO.getName());
            preparedStatement.setString(2, generalItemDTO.getDescription());
            preparedStatement.setString(3, generalItemDTO.getType());
            preparedStatement.setString(4, generalItemDTO.getImage());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
