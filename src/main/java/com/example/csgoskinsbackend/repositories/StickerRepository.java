package com.example.csgoskinsbackend.repositories;

import com.example.csgoskinsbackend.models.DTOs.PagedResponseDTO;
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

import static com.example.csgoskinsbackend.utils.TypeMapper.mapItemFromResultSet;

@Repository
public class StickerRepository {
    private static final Integer PAGE_SIZE = 50;
    private final JdbcTemplate jdbcTemplate;
    public StickerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public PagedResponseDTO getStickersByTournament(String tournament, Integer page) {
        final int[] totalItems = {0};
        List<GeneralItemDTO> items = this.jdbcTemplate.query("SELECT g.*, s.*, COUNT(*) OVER() as total_count FROM general_items g JOIN stickers s ON g.id = s.id WHERE g.name ILIKE '%"+tournament+"%' LIMIT ? OFFSET ?",
                (resultSet, rowNum) -> {
                    totalItems[0] = resultSet.getInt("total_count");
                    return mapItemFromResultSet(resultSet, "sticker");
                },
                PAGE_SIZE, PAGE_SIZE*page
        );
        Integer totalPages = (int) Math.ceil((double) totalItems[0] / PAGE_SIZE);
        return new PagedResponseDTO(items, page, totalPages, totalItems[0]);
    }
}
