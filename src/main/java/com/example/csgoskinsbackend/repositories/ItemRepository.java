package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.*;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.csgoskinsbackend.utils.TypeMapper.getTypeTable;
import static com.example.csgoskinsbackend.utils.TypeMapper.mapItemFromResultSet;

@Repository
public class ItemRepository {
    private final JdbcTemplate jdbcTemplate;
    public ItemRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Map<Integer, CollectionDTO> getAllCollections(List<Integer> ids){
        if (ids.isEmpty()){
            return new HashMap<>();
        }
        Map<Integer, CollectionDTO> collectionMap = this.jdbcTemplate.query(
                "SELECT item_collections.item_id, collections.id, collections.name, collections.image, collections.date_added FROM item_collections JOIN collections ON item_collections.collection_id = collections.id WHERE item_collections.item_id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, CollectionDTO> map = new HashMap<>();
                    while (resultSet.next()){
                        Integer itemId = resultSet.getInt("item_id");
                        CollectionDTO collectionDTO = new CollectionDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("image"), resultSet.getDate("date_added").toLocalDate());
                        map.put(itemId, collectionDTO);
                    }
                    return map;
                }
        );
        return collectionMap;
    }
    public CollectionDTO getCollection(Integer id){
        if (id == null){
            return new CollectionDTO();
        }
        return this.jdbcTemplate.query(
                "SELECT item_collections.item_id, collections.id, collections.name, collections.image, collections.date_added FROM item_collections JOIN collections ON item_collections.collection_id = collections.id WHERE item_collections.item_id = ?",
                (resultSet, rowNum) -> {
                    CollectionDTO collectionDTO = new CollectionDTO();
                    collectionDTO.setId(resultSet.getInt("id"));
                    collectionDTO.setName(resultSet.getString("name"));
                    collectionDTO.setImage(resultSet.getString("image"));
                    collectionDTO.setDateAdded(resultSet.getDate("date_added").toLocalDate());
                    return collectionDTO;
                },
                id
        ).stream().findFirst().orElse(null);
    }
    public CollectionDTO getCollectionById(Integer id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT id, name, image, date_added FROM collections WHERE id = ?",
                (resultSet, rowNum) -> {
                    CollectionDTO collectionDTO = new CollectionDTO();
                    collectionDTO.setId(resultSet.getInt("id"));
                    collectionDTO.setName(resultSet.getString("name"));
                    collectionDTO.setImage(resultSet.getString("image"));
                    collectionDTO.setDateAdded(resultSet.getDate("date_added").toLocalDate());
                    return collectionDTO;
                },
                id
        );
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
    public List<CrateDTO> getCrates(Integer id){
        if (id == null){
            return new ArrayList<>();
        }
        return this.jdbcTemplate.query(
                "SELECT item_crates.item_id, crates.id, crates.name, crates.image, crates.date_added FROM item_crates JOIN crates ON item_crates.crate_id = crates.id WHERE item_crates.item_id = ?",
                (resultSet, rowNum) -> {
                    CrateDTO crateDTO = new CrateDTO();
                    crateDTO.setId(resultSet.getInt("id"));
                    crateDTO.setName(resultSet.getString("name"));
                    crateDTO.setImage(resultSet.getString("image"));
                    crateDTO.setDateAdded(resultSet.getDate("date_added").toLocalDate());
                    return crateDTO;
                },
                id
        );
    }
    public CrateDTO getCrateById(Integer id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT id, name, image, date_added FROM crates WHERE id = ?",
                (resultSet, rowNum) -> {
                    CrateDTO crateDTO = new CrateDTO();
                    crateDTO.setId(resultSet.getInt("id"));
                    crateDTO.setName(resultSet.getString("name"));
                    crateDTO.setImage(resultSet.getString("image"));
                    crateDTO.setDateAdded(resultSet.getDate("date_added").toLocalDate());
                    return crateDTO;
                },
                id
        );
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
                    "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?)",
                    new String[]{"id"}
            );
            preparedStatement.setString(1, generalItemDTO.getName());
            preparedStatement.setString(2, generalItemDTO.getDescription());
            preparedStatement.setString(3, generalItemDTO.getType());
            preparedStatement.setString(4, generalItemDTO.getImage());
            preparedStatement.setString(5, generalItemDTO.getRarity());
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
    public List<GeneralItemDTO> getItemsByCollection(Integer collectionId) {
        String type = this.jdbcTemplate.queryForObject(
                "SELECT type FROM collections WHERE id = ?",
                String.class,
                collectionId
        );
        String typeTable = getTypeTable(type);
        return this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, t.* " +
                        "FROM general_items " +
                        "JOIN " + typeTable + " t ON general_items.id = t.id " +
                        "JOIN item_collections ON general_items.id = item_collections.item_id " +
                        "WHERE item_collections.collection_id = ?",
                (resultSet, rowNum) -> mapItemFromResultSet(resultSet, type),
                collectionId
        );
    }
    public List<GeneralItemDTO> getItemsByCrate(Integer crateId) {
        String type = this.jdbcTemplate.queryForObject(
                "SELECT type FROM crates WHERE id = ?",
                String.class,
                crateId
        );
        String typeTable = getTypeTable(type);
        return this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, t.* " +
                        "FROM general_items " +
                        "JOIN " + typeTable + " t ON general_items.id = t.id " +
                        "JOIN item_crates ON general_items.id = item_crates.item_id " +
                        "WHERE item_crates.crate_id = ?",
                (resultSet, rowNum) -> mapItemFromResultSet(resultSet, type),
                crateId
        );
    }
    public void linkCollection(Integer itemId, Integer collectionId) {
        jdbcTemplate.update(
                "INSERT INTO item_collections (item_id, collection_id) VALUES (?, ?)",
                itemId, collectionId
        );
    }

    public void linkCrate(Integer itemId, Integer crateId) {
        jdbcTemplate.update(
                "INSERT INTO item_crates (item_id, crate_id) VALUES (?, ?)",
                itemId, crateId
        );
    }

    public GeneralItemDTO getItemById(Integer id) {
        String type = this.jdbcTemplate.queryForObject(
                "SELECT type FROM general_items WHERE id = ?",
                String.class,
                id
        );
        String typeTable = getTypeTable(type);
        return this.jdbcTemplate.queryForObject(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, t.* " +
                        "FROM general_items " +
                        "JOIN " + typeTable + " t ON general_items.id = t.id " +
                        "WHERE general_items.id = ?",
                (resultSet, rowNum) -> mapItemFromResultSet(resultSet, type),
                id
        );
    }

    public Integer getTotalItemsCount() {
        return this.jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM general_items",
                Integer.class
        );
    }

    public Map<String, List<Integer>> getAllItemIds() {
        return this.jdbcTemplate.query(
                "SELECT id, type\n" +
                        "FROM (\n" +
                        "    SELECT \n" +
                        "        id, \n" +
                        "        type,\n" +
                        "        ROW_NUMBER() OVER (PARTITION BY type ORDER BY id) as row_num\n" +
                        "    FROM general_items\n" +
                        ") sub\n" +
                        "WHERE row_num <= 30;",
                resultSet -> {
                    Map<String, List<Integer>> map = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        String type = resultSet.getString("type");
                        Integer itemId = resultSet.getInt("id");
                        if (!map.containsKey(type)) {
                            map.put(type, new ArrayList<>());
                        }
                        map.get(type).add(itemId);
                    }
                    return map;
                }
        );
    }

    public Map<Integer, GeneralItemDTO> getItemsByIdAndTable(List<Integer> ids, String typeTable) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        return this.jdbcTemplate.query(
                "SELECT general_items.*, " + typeTable + ".* FROM general_items JOIN " + typeTable + " ON general_items.id = " + typeTable + ".id WHERE general_items.id IN (" +
                        ids.stream().map(id -> String.valueOf(id)).collect(Collectors.joining(", ")) +
                        ")",
                resultSet -> {
                    Map<Integer, GeneralItemDTO> map = new HashMap<>();
                    while (resultSet.next()) {
                        GeneralItemDTO dto = mapItemFromResultSet(resultSet, resultSet.getString("type"));
                        map.put(dto.getId(), dto);
                    }
                    return map;
                }
        );
    }
    public Map<String, List<Integer>> getItemIdsByName(String searchName) {
        return this.jdbcTemplate.query("SELECT id, type FROM general_items WHERE name ILIKE '%"+searchName+" |%'",
                resultSet -> {
                    Map<String, List<Integer>> map = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        String type = resultSet.getString("type");
                        Integer itemId = resultSet.getInt("id");
                        if (!map.containsKey(type)) {
                            map.put(type, new ArrayList<>());
                        }
                        map.get(type).add(itemId);
                    }
                    return map;
                }
        );
    }
}
