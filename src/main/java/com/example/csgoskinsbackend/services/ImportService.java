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

    public void importCollections(String filePath) throws IOException {
        this.importRepository.importCollections(filePath);
    }
    public void importCrates(String filePath) throws IOException {
        this.importRepository.importCrates(filePath);
    }
    public void importSkins(String filePath) throws IOException {
        this.importRepository.importSkins(filePath);
    }
}
