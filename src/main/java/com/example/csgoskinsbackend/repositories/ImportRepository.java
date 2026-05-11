package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.fileDTOs.*;
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
    @Transactional
    public void importStickers(String filePath) throws IOException {
        List<StickerFileDTO> stickers = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<StickerFileDTO>>() {}
        );

        for (StickerFileDTO sticker : stickers) {
            // 1. Вставка в general_items
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";
            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    sticker.name(),
                    sticker.description(),
                    "sticker",
                    sticker.image(),
                    sticker.rarity() != null ? sticker.rarity().name() : null
            );

            // 2. Вставка в stickers
            String sqlSticker = "INSERT INTO stickers (id, def_index, sticker_type, effect, tournament, team, player) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sqlSticker,
                    itemId,
                    sticker.defIndex() != null ? Integer.parseInt(sticker.defIndex()) : null,
                    sticker.type(),
                    sticker.effect(),
                    sticker.tournament() != null ? sticker.tournament().name() : null,
                    sticker.team() != null ? sticker.team().name() : null,
                    sticker.player() != null ? sticker.player().name() : null
            );

            // 3. Связь с коллекциями через INSERT ... SELECT
            if (sticker.collections() != null) {
                for (StickerFileDTO.CollectionDTO coll : sticker.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            // 4. Связь с кейсами через INSERT ... SELECT
            if (sticker.crates() != null) {
                for (StickerFileDTO.CrateDTO crate : sticker.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importAgents(String filePath) throws IOException {
        List<AgentFileDTO> agents = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<AgentFileDTO>>() {}
        );

        for (AgentFileDTO agent : agents) {
            // 1. Добавляем общие данные
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    agent.name(),
                    agent.description(),
                    "agent",
                    agent.image(),
                    agent.rarity() != null ? agent.rarity().name() : null
            );

            // 2. Добавляем специфичные данные агента
            String sqlAgent = "INSERT INTO agents (id, def_index, team) VALUES (?, ?, ?)";

            jdbcTemplate.update(sqlAgent,
                    itemId,
                    agent.defIndex() != null ? Integer.parseInt(agent.defIndex()) : null,
                    agent.team() != null ? agent.team().name() : null
            );

            // 3. Связи с коллекциями через INSERT ... SELECT по имени
            if (agent.collections() != null) {
                for (AgentFileDTO.CollectionDTO coll : agent.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            // 4. Связи с кейсами через INSERT ... SELECT по имени
            if (agent.crates() != null) {
                for (AgentFileDTO.CrateDTO crate : agent.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importCollectibles(String filePath) throws IOException {
        List<CollectibleFileDTO> allItems = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<CollectibleFileDTO>>() {}
        );

        // Фильтруем: только те, где type == "Pin"
        List<CollectibleFileDTO> pins = allItems.stream()
                .filter(item -> item.type() != null && item.type().equalsIgnoreCase("Pin"))
                .toList();

        for (CollectibleFileDTO pin : pins) {
            // 1. Добавляем в general_items
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    pin.name(),
                    pin.description(),
                    "collectible", // фиксируем тип для общей таблицы
                    pin.image(),
                    pin.rarity() != null ? pin.rarity().name() : null
            );

            // 2. Добавляем в collectibles
            String sqlCollectible = "INSERT INTO collectibles (id, def_index, collectible_type, is_genuine) VALUES (?, ?, ?, ?)";

            jdbcTemplate.update(sqlCollectible,
                    itemId,
                    pin.defIndex() != null ? Integer.parseInt(pin.defIndex()) : null,
                    pin.type(), // сюда пишем "Pin"
                    pin.genuine() != null ? pin.genuine() : false
            );

            // 3. Связи с коллекциями
            if (pin.collections() != null) {
                for (CollectibleFileDTO.CollectionDTO coll : pin.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            // 4. Связи с контейнерами (crates)
            if (pin.crates() != null) {
                for (CollectibleFileDTO.CrateDTO crate : pin.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importGraffiti(String filePath) throws IOException {
        List<GraffitiFileDTO> graffitiList = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<GraffitiFileDTO>>() {}
        );

        for (GraffitiFileDTO graffiti : graffitiList) {
            // 1. Добавляем в general_items
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    graffiti.name(),
                    graffiti.description(),
                    "graffiti",
                    graffiti.image(),
                    graffiti.rarity() != null ? graffiti.rarity().name() : null
            );

            // 2. Добавляем в таблицу graffiti
            String sqlGraffiti = "INSERT INTO graffiti (id, def_index) VALUES (?, ?)";
            jdbcTemplate.update(sqlGraffiti,
                    itemId,
                    graffiti.defIndex() != null ? Integer.parseInt(graffiti.defIndex()) : null
            );

            // 3. Добавляем связи с контейнерами (crates) по имени
            if (graffiti.crates() != null) {
                for (GraffitiFileDTO.CrateDTO crate : graffiti.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }

            // 4. Добавляем связи с коллекциями (если они есть в данных)
            if (graffiti.collections() != null) {
                for (GraffitiFileDTO.CollectionDTO coll : graffiti.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importKeychains(String filePath) throws IOException {
        List<KeychainFileDTO> keychains = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<KeychainFileDTO>>() {}
        );

        for (KeychainFileDTO keychain : keychains) {
            // 1. Добавляем в general_items
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    keychain.name(),
                    keychain.description(),
                    "keychain",
                    keychain.image(),
                    keychain.rarity() != null ? keychain.rarity().name() : null
            );

            // 2. Добавляем в таблицу keychains
            String sqlKeychain = "INSERT INTO keychains (id, def_index) VALUES (?, ?)";
            jdbcTemplate.update(sqlKeychain,
                    itemId,
                    keychain.defIndex() != null ? Integer.parseInt(keychain.defIndex()) : null
            );

            // 3. Связи с коллекциями
            if (keychain.collections() != null) {
                for (KeychainFileDTO.CollectionDTO coll : keychain.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            // 4. Связи с контейнерами (crates)
            if (keychain.crates() != null) {
                for (KeychainFileDTO.CrateDTO crate : keychain.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importKeys(String filePath) throws IOException {
        List<KeyFileDTO> keys = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<KeyFileDTO>>() {}
        );

        for (KeyFileDTO key : keys) {
            // 1. Добавляем в general_items
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    key.name(),
                    key.description(),
                    "key",
                    key.image(),
                    key.rarity() != null ? key.rarity().name() : null
            );

            // 2. Добавляем в таблицу keys
            String sqlKey = "INSERT INTO keys (id, def_index, marketable) VALUES (?, ?, ?)";
            jdbcTemplate.update(sqlKey,
                    itemId,
                    key.defIndex() != null ? Integer.parseInt(key.defIndex()) : null,
                    key.marketable() != null ? key.marketable() : true
            );

            // 3. Связи с контейнерами (crates) - для ключей это основной функционал
            if (key.crates() != null) {
                for (KeyFileDTO.CrateDTO crate : key.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }

            // 4. Связи с коллекциями (на всякий случай, если будут в JSON)
            if (key.collections() != null) {
                for (KeyFileDTO.CollectionDTO coll : key.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importMusicKits(String filePath) throws IOException {
        List<MusicKitFileDTO> musicKits = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<MusicKitFileDTO>>() {}
        );

        for (MusicKitFileDTO kit : musicKits) {
            // 1. Добавляем в общую таблицу
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    kit.name(),
                    kit.description(),
                    "music_kit",
                    kit.image(),
                    kit.rarity() != null ? kit.rarity().name() : null
            );

            // 2. Добавляем специфичные данные набора
            String sqlKit = "INSERT INTO music_kits (id, def_index, exclusive) VALUES (?, ?, ?)";
            jdbcTemplate.update(sqlKit,
                    itemId,
                    kit.defIndex() != null ? Integer.parseInt(kit.defIndex()) : null,
                    kit.exclusive() != null ? kit.exclusive() : false
            );

            // 3. Связи с коллекциями
            if (kit.collections() != null) {
                for (MusicKitFileDTO.CollectionDTO coll : kit.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }

            // 4. Связи с контейнерами (например, кейсы с музыкой)
            if (kit.crates() != null) {
                for (MusicKitFileDTO.CrateDTO crate : kit.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }
        }
    }
    @Transactional
    public void importPatches(String filePath) throws IOException {
        List<PatchFileDTO> patches = objectMapper.readValue(
                new File(filePath),
                new TypeReference<List<PatchFileDTO>>() {}
        );

        for (PatchFileDTO patch : patches) {
            // 1. Добавляем в общую таблицу предметов
            String sqlGeneral = "INSERT INTO general_items (name, description, type, image, rarity) VALUES (?, ?, ?, ?, ?) RETURNING id";

            Integer itemId = jdbcTemplate.queryForObject(sqlGeneral, Integer.class,
                    patch.name(),
                    patch.description(),
                    "patch",
                    patch.image(),
                    patch.rarity() != null ? patch.rarity().name() : null
            );

            // 2. Добавляем в специфичную таблицу нашивок
            String sqlPatch = "INSERT INTO patches (id, def_index) VALUES (?, ?)";
            jdbcTemplate.update(sqlPatch,
                    itemId,
                    patch.defIndex() != null ? Integer.parseInt(patch.defIndex()) : null
            );

            // 3. Проставляем связи с контейнерами (Patch Packs)
            if (patch.crates() != null) {
                for (PatchFileDTO.CrateDTO crate : patch.crates()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_crates (item_id, crate_id) " +
                                    "SELECT ?, id FROM crates WHERE name = ?",
                            itemId,
                            crate.name()
                    );
                }
            }

            // 4. Проставляем связи с коллекциями
            if (patch.collections() != null) {
                for (PatchFileDTO.CollectionDTO coll : patch.collections()) {
                    jdbcTemplate.update(
                            "INSERT INTO item_collections (item_id, collection_id) " +
                                    "SELECT ?, id FROM collections WHERE name = ?",
                            itemId,
                            coll.name()
                    );
                }
            }
        }
    }
}
