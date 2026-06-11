package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.repositories.ImportRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImportService {

    private final ImportRepository importRepository;

    public ImportService(ImportRepository importRepository) {
        this.importRepository = importRepository;
    }

    public void importPrices(String filePath) throws IOException {
        this.importRepository.importPrices(filePath);
    }
    public void importCollections(String filePath) throws IOException {
        this.importRepository.importCollections(filePath);
    }
    public void importCrates(String filePath) throws IOException {
        this.importRepository.importCrates(filePath);
    }
    public void importSkins(String filePath) throws IOException {
        this.importRepository.importSkins(filePath);
    }
    public void importStickers(String filePath) throws IOException {
        this.importRepository.importStickers(filePath);
    }
    public void importAgents(String filePath) throws IOException {
        this.importRepository.importAgents(filePath);
    }
    public void importCollectibles(String filePath) throws IOException {
        this.importRepository.importCollectibles(filePath);
    }
    public void importGraffiti(String filePath) throws IOException {
        this.importRepository.importGraffiti(filePath);
    }
    public void importKeychains(String filePath) throws IOException {
        this.importRepository.importKeychains(filePath);
    }
    public void importKeys(String filePath) throws IOException {
        this.importRepository.importKeys(filePath);
    }
    public void importMusicKits(String filePath) throws IOException {
        this.importRepository.importMusicKits(filePath);
    }
    public void importPatches(String filePath) throws IOException {
        this.importRepository.importPatches(filePath);
    }
}
