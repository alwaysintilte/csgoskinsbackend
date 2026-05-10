package com.example.csgoskinsbackend.models.records;

public record MarketLink(
        Integer id,
        Integer weaponId,
        String wear,
        Boolean stattrak,
        Boolean souvenir,
        String link
) { }
