package com.example.csgoskinsbackend.utils;

import com.example.csgoskinsbackend.models.DTOs.WeaponDTO;
import com.example.csgoskinsbackend.repositories.ImportRepository;
import com.example.csgoskinsbackend.services.ImportService;
import com.example.csgoskinsbackend.services.MarketLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataImportRunner implements CommandLineRunner {

    private final ImportService importService;
    private final MarketLinkService marketLinkService;
    @Autowired
    public DataImportRunner(ImportService importService, MarketLinkService marketLinkService) {
        this.importService = importService;
        this.marketLinkService = marketLinkService;
    }
    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/data/skins.json";
        //System.out.println("Начинается импорт кейсов...");
        //importService.importCollections(filePath);
        //importService.importCrates(filePath);
        //importService.importSkins(filePath);
        //System.out.println("Импорт завершен успешно!");
        System.out.println("Импорт нету");
    }
}
