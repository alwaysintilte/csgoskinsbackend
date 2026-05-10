package com.example.csgoskinsbackend.models.records;

import jakarta.persistence.*;

public record GeneralItem(
        Integer id,
        String name,
        String description,
        String type,
        String image,
        String rarity,
        Integer collectionId
) { }
