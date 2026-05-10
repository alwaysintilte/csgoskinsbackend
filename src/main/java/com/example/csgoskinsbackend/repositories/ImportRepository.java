package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.fileDTOs.CollectionFileDTO;
import com.example.csgoskinsbackend.models.fileDTOs.CrateFileDTO;
import com.example.csgoskinsbackend.models.fileDTOs.SkinFileDTO;
import com.example.csgoskinsbackend.models.fileDTOs.SkinFileDTO.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ImportRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public ImportRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }
    @Transactional
    public void importCollections(String filePath) throws IOException {
        List<CollectionFileDTO> collections = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<CollectionFileDTO>>() {}
        );

        String sql = "INSERT INTO collections (name, image, date_added, type) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                collections,
                100,
                (ps, collection) -> {
                    ps.setString(1, collection.name());
                    ps.setString(2, collection.imageUrl());
                    ps.setDate(3, Date.valueOf(LocalDate.now()));
                    ps.setString(4, "weapon");
                });
    }
    @Transactional
    public void importCrates(String filePath) throws IOException {
        List<CrateFileDTO> crates = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<CrateFileDTO>>() {}
        );

        String sql = "INSERT INTO crates (name, image, date_added, type) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                crates,
                100,
                (ps, crate) -> {
                    ps.setString(1, crate.name());
                    ps.setString(2, crate.imageUrl());
                    ps.setDate(3, Date.valueOf(LocalDate.now()));
                    ps.setString(4, "weapon");
                });
    }
    @Transactional
    public void importSkins(String filePath) throws IOException {
        List<SkinFileDTO> skins = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<SkinFileDTO>>() {}
        );

        for (SkinFileDTO skin : skins) {
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";
            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    skin.name(),
                    skin.description(),
                    "weapon",
                    skin.image(),
                    skin.rarity().name()
            );

            String[] wearNames = (skin.wears() != null)
                    ? skin.wears().stream().map(WearDTO::name).toArray(String[]::new)
                    : new String[0];
            String sqlWeapon = "INSERT INTO weapons (id, weapon, category, min_float, max_float, stattrak, souvenir, paint_index, wears) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlWeapon, ps -> {
                ps.setInt(1, itemId);
                ps.setString(2, skin.weapon() != null ? skin.weapon().name() : "null");
                ps.setString(3, skin.category() != null ? skin.category().name() : "null");
                ps.setObject(4, skin.minFloat());
                ps.setObject(5, skin.maxFloat());
                ps.setObject(6, skin.stattrak());
                ps.setObject(7, skin.souvenir());
                ps.setObject(8, skin.paintIndex() != null ? Integer.parseInt(skin.paintIndex()) : null);
                ps.setArray(9, ps.getConnection().createArrayOf("text", wearNames));
            });

            if (skin.collections() != null) {
                for (CollectionDTO coll : skin.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            if (skin.crates() != null) {
                for (CrateDTO crate : skin.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
}
