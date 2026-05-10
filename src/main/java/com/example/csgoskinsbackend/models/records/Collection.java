package com.example.csgoskinsbackend.models.records;

import java.time.LocalDate;

public record Collection(
        Integer id,
        String name,
        String image,
        LocalDate dateAdded,
        String type
) { }
