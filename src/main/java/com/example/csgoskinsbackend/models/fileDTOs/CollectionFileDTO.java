package com.example.csgoskinsbackend.models.fileDTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CollectionFileDTO(
        String name,
        @JsonProperty("image") String imageUrl
) {}
