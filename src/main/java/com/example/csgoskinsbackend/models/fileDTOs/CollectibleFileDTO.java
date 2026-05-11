package com.example.csgoskinsbackend.models.fileDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CollectibleFileDTO(
        String name,
        String description,
        String image,
        @JsonProperty("def_index") String defIndex,
        String type,
        Boolean genuine,
        RarityDTO rarity,
        List<CollectionDTO> collections,
        List<CrateDTO> crates
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RarityDTO(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CollectionDTO(String name) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CrateDTO(String name) {}
}