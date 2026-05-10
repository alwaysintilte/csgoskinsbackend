package com.example.csgoskinsbackend.models.records;

import jakarta.persistence.*;

import java.util.List;

public record Weapon(
        Integer id,
        String weapon,
        String category,
        Double minFloat,
        Double maxFloat,
        Boolean stattrak,
        Boolean souvenir,
        Integer paintIndex,
        List<String> wears
) { }
