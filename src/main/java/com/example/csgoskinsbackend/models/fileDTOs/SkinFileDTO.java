package com.example.csgoskinsbackend.models.fileDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SkinFileDTO(
        String name,
        String description,
        String image,
        WeaponDTO weapon,
        CategoryDTO category,
        RarityDTO rarity,
        @JsonProperty("min_float") Double minFloat,
        @JsonProperty("max_float") Double maxFloat,
        @JsonProperty("paint_index") String paintIndex,
        Boolean stattrak,
        Boolean souvenir,
        List<WearDTO> wears,
        List<CollectionDTO> collections,
        List<CrateDTO> crates
) {
    // Вложенные рекорды должны быть public, чтобы репозиторий их видел
    @JsonIgnoreProperties(ignoreUnknown = true) public record WeaponDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record CategoryDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record RarityDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record WearDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record CollectionDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record CrateDTO(String name) {}
}
