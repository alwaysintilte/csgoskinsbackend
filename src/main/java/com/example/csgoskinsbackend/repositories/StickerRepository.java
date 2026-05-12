package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.models.DTOs.items.WeaponDTO;
import com.example.csgoskinsbackend.utils.TypeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StickerRepository {
    private final JdbcTemplate jdbcTemplate;
    public StickerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<GeneralItemDTO> getStickersByTournament(String tournament) {
        System.out.println(tournament);
        return this.jdbcTemplate.query("SELECT g.*, s.* FROM general_items g JOIN stickers s ON g.id = s.id WHERE g.name ILIKE '%"+tournament+"%'",
                (rs, rowNum) -> TypeMapper.mapItemFromResultSet(rs, "sticker")
        );
    }
}
