package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.repositories.ItemRepository;
import com.example.csgoskinsbackend.repositories.StickerRepository;
import com.example.csgoskinsbackend.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StickerService {
    private final ItemRepository itemRepository;
    private final StickerRepository stickerRepository;
    private final MarketLinkService marketLinkService;
    @Autowired
    public StickerService(ItemRepository itemRepository, StickerRepository stickerRepository, MarketLinkService marketLinkService){
        this.itemRepository = itemRepository;
        this.stickerRepository = stickerRepository;
        this.marketLinkService = marketLinkService;
    }
    public List<GeneralItemDTO> getStickersByTournament(String tournament) {
        List<GeneralItemDTO> items = this.stickerRepository.getStickersByTournament(tournament);
        List<Integer> ids = items.stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : items) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
        }
        return items;
    }
}
