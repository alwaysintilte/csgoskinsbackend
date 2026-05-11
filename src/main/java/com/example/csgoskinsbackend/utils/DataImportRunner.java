package com.example.csgoskinsbackend.utils;

import com.example.csgoskinsbackend.services.ImportService;
import com.example.csgoskinsbackend.services.MarketLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//        String filePath = "src/main/resources/data/.json";
//        System.out.println("Начинается импорт ...");
//        importService.importCollections(filePath);
//        importService.importCrates(filePath);
//        importService.importSkins(filePath);
//        importService.importStickers(filePath);
//        importService.importAgents(filePath);
//        importService.importCollectibles(filePath);
//        importService.importGraffiti(filePath);
//        importService.importKeychains(filePath);
//        importService.importKeys(filePath);
//        importService.importMusicKits(filePath);
//        importService.importPatches(filePath);
//        System.out.println("Импорт завершен успешно!");
        System.out.println("Импорта нету");
    }
}
