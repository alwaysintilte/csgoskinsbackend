package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.*;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.utils.TypeMapper;
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
    private static final Integer PAGE_SIZE = 50;
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
    public PagedResponseDTO getItemsByCollection(Integer collectionId, Integer page) {
        String type = this.jdbcTemplate.queryForObject(
                "SELECT type FROM collections WHERE id = ?",
                String.class,
                collectionId
        );
        String typeTable = getTypeTable(type);
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, t.*, COUNT(*) OVER() as total_count " +
                        "FROM general_items " +
                        "JOIN " + typeTable + " t ON general_items.id = t.id " +
                        "JOIN item_collections ON general_items.id = item_collections.item_id " +
                        "WHERE item_collections.collection_id = ? LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, type);
                },
                collectionId, PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
    }
    public PagedResponseDTO getItemsByCrate(Integer crateId, Integer page) {
        String type = this.jdbcTemplate.queryForObject(
                "SELECT type FROM crates WHERE id = ?",
                String.class,
                crateId
        );
        String typeTable = getTypeTable(type);
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query(
                "SELECT general_items.id, general_items.name, general_items.description, general_items.type, general_items.image, general_items.rarity, t.*, COUNT(*) OVER() as total_count " +
                        "FROM general_items " +
                        "JOIN " + typeTable + " t ON general_items.id = t.id " +
                        "JOIN item_crates ON general_items.id = item_crates.item_id " +
                        "WHERE item_crates.crate_id = ? LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, type);
                },
                crateId, PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
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

    public PagedResponseDTO getAllItems(Integer page) {
        final int[] totalItems = {0};
        Map<String, List<Integer>> idsByType = this.jdbcTemplate.query(
                "SELECT id, type, COUNT(*) OVER() as total_count FROM general_items LIMIT ? OFFSET ?",
                resultSet -> {
                    Map<String, List<Integer>> map = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        totalItems[0] = resultSet.getInt("total_count");
                        String type = resultSet.getString("type");
                        Integer itemId = resultSet.getInt("id");
                        if (!map.containsKey(type)) {
                            map.put(type, new ArrayList<>());
                        }
                        map.get(type).add(itemId);
                    }
                    return map;
                },
                PAGE_SIZE, PAGE_SIZE * page
        );
        Map<Integer, GeneralItemDTO> itemsById = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : idsByType.entrySet()) {
            String typeTable = getTypeTable(entry.getKey());
            List<Integer> ids = entry.getValue();
            if (!ids.isEmpty()) {
                itemsById.putAll(getItemsByIdAndTable(ids, typeTable));
            }
        }
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(new ArrayList<>(itemsById.values()), page, totalPages, totalItems[0]);
    }
    public PagedResponseDTO searchAllItemsByName(Integer page, String name) {
        final int[] totalItems = {0};
        Map<String, List<Integer>> idsByType = this.jdbcTemplate.query(
                "SELECT id, type, COUNT(*) OVER() as total_count FROM general_items WHERE name ILIKE '%"+name+"%' LIMIT ? OFFSET ?",
                resultSet -> {
                    Map<String, List<Integer>> map = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        totalItems[0] = resultSet.getInt("total_count");
                        String type = resultSet.getString("type");
                        Integer itemId = resultSet.getInt("id");
                        if (!map.containsKey(type)) {
                            map.put(type, new ArrayList<>());
                        }
                        map.get(type).add(itemId);
                    }
                    return map;
                },
                PAGE_SIZE, PAGE_SIZE * page
        );
        Map<Integer, GeneralItemDTO> itemsById = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : idsByType.entrySet()) {
            String typeTable = getTypeTable(entry.getKey());
            List<Integer> ids = entry.getValue();
            if (!ids.isEmpty()) {
                itemsById.putAll(getItemsByIdAndTable(ids, typeTable));
            }
        }
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(new ArrayList<>(itemsById.values()), page, totalPages, totalItems[0]);
    }
    public PagedResponseDTO searchAllItemsByFilters(Integer page, ItemSearchFilters itemSearchFilters) {
        final int[] totalItems = {0};
        String namePat = itemSearchFilters.getName() != null ? "%" + itemSearchFilters.getName() + "%" : null;
        String tourPat = itemSearchFilters.getTournament() != null ? "%" + itemSearchFilters.getTournament() + "%" : null;

        Object[] args = {
                namePat, namePat,
                itemSearchFilters.getType(), itemSearchFilters.getType(),
                itemSearchFilters.getRarity(), itemSearchFilters.getRarity(),
                itemSearchFilters.getWeapon(), itemSearchFilters.getWear(),
                itemSearchFilters.getMinFloat(), itemSearchFilters.getMaxFloat(),
                itemSearchFilters.isStatTrak(), itemSearchFilters.isSouvenir(),
                itemSearchFilters.getWeapon(), itemSearchFilters.getWeapon(),
                itemSearchFilters.getWear(), itemSearchFilters.getWear(),
                itemSearchFilters.getMinFloat(), itemSearchFilters.getMinFloat(),
                itemSearchFilters.getMaxFloat(), itemSearchFilters.getMaxFloat(),
                itemSearchFilters.isStatTrak(), itemSearchFilters.isStatTrak(),
                itemSearchFilters.isSouvenir(), itemSearchFilters.isSouvenir(),
                itemSearchFilters.getStickerType(), tourPat,
                itemSearchFilters.getStickerType(), itemSearchFilters.getStickerType(),
                tourPat, tourPat,

                PAGE_SIZE, PAGE_SIZE * page
        };
        Map<String, List<Integer>> idsByType = this.jdbcTemplate.query(
                """
                    SELECT g.id, g.type, COUNT(*) OVER() as total_count
                    FROM general_items g
                    WHERE 1=1
                    AND (g.name ILIKE ? OR ?::text IS NULL)
                    AND (g.type = ? OR ?::text IS NULL)
                    AND (g.rarity = ? OR ?::text IS NULL)
                    
                    AND (
                        (
                            ?::text IS NULL AND ?::text IS NULL AND ?::double precision IS NULL
                            AND ?::double precision IS NULL AND ?::boolean IS NULL AND ?::boolean IS NULL
                        )
                        OR (
                            g.type = 'weapon' AND EXISTS (
                                SELECT 1 FROM weapons w WHERE w.id = g.id
                                AND (w.weapon = ? OR ?::text IS NULL)
                                AND (?::text = ANY(w.wears) OR ?::text IS NULL)
                                AND (w.min_float >= ? OR ?::double precision IS NULL)
                                AND (w.max_float <= ? OR ?::double precision IS NULL)
                                AND (w.stattrak = ? OR ?::boolean IS NULL)
                                AND (w.souvenir = ? OR ?::boolean IS NULL)
                            )
                        )
                    )
            
                    AND (
                        (
                            ?::text IS NULL AND ?::text IS NULL
                        )
                        OR (
                            g.type = 'sticker' AND EXISTS (
                                SELECT 1 FROM stickers s WHERE s.id = g.id
                                AND (s.sticker_type = ? OR ?::text IS NULL)
                                AND (s.tournament ILIKE ? OR ?::text IS NULL)
                            )
                        )
                    )
                    LIMIT ? OFFSET ?
                    """,
                resultSet -> {
                    Map<String, List<Integer>> map = new LinkedHashMap<>();
                    while (resultSet.next()) {
                        totalItems[0] = resultSet.getInt("total_count");
                        String type = resultSet.getString("type");
                        Integer itemId = resultSet.getInt("id");
                        if (!map.containsKey(type)) {
                            map.put(type, new ArrayList<>());
                        }
                        map.get(type).add(itemId);
                    }
                    return map;
                },
                args
        );
        Map<Integer, GeneralItemDTO> itemsById = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : idsByType.entrySet()) {
            String typeTable = getTypeTable(entry.getKey());
            List<Integer> ids = entry.getValue();
            if (!ids.isEmpty()) {
                itemsById.putAll(getItemsByIdAndTable(ids, typeTable));
            }
        }
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(new ArrayList<>(itemsById.values()), page, totalPages, totalItems[0]);
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
    public PagedResponseDTO getItemsByTable(String typeTable, Integer page) {
        typeTable = getTypeTable(typeTable);
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query(
                "SELECT general_items.*, " + typeTable + ".*, COUNT(*) OVER() as total_count FROM general_items JOIN " + typeTable + " ON general_items.id = " + typeTable + ".id LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, resultSet.getString("type"));
                }, PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
    }
    public PagedResponseDTO getCollectionsByType(String type, Integer page) {
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query("SELECT *, COUNT(*) OVER() as total_count FROM collections WHERE type = ? LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, "collection");
                },
                type, PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
    }
    public PagedResponseDTO getCratesByType(String type, Integer page) {
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query("SELECT *, COUNT(*) OVER() as total_count FROM crates WHERE type = ? LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, "container");
                },
                type, PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
    }
}
