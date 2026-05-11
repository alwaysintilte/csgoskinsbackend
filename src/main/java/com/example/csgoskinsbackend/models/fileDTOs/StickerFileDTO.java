package com.example.csgoskinsbackend.models.fileDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StickerFileDTO(
        String name,
        String description,
        String image,
        String type,
        String effect,
        @JsonProperty("def_index") String defIndex,
        RarityDTO rarity,
        TournamentDTO tournament,
        TeamDTO team,
        PlayerDTO player,
        List<CrateDTO> crates,
        List<CollectionDTO> collections
) {
    @JsonIgnoreProperties(ignoreUnknown = true) public record RarityDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record TournamentDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record TeamDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record PlayerDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record CollectionDTO(String name) {}
    @JsonIgnoreProperties(ignoreUnknown = true) public record CrateDTO(String name) {}
}
