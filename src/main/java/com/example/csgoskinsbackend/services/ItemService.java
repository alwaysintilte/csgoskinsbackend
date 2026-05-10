package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.GeneralItemDTO;
import com.example.csgoskinsbackend.models.DTOs.WeaponDTO;
import com.example.csgoskinsbackend.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.csgoskinsbackend.repositories.ItemRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final WeaponRepository weaponRepository;
    private final MarketLinkService marketLinkService;
    @Autowired
    public ItemService(ItemRepository itemRepository, WeaponRepository weaponRepository, MarketLinkService marketLinkService){
        this.itemRepository = itemRepository;
        this.weaponRepository = weaponRepository;
        this.marketLinkService = marketLinkService;
    }
    public Integer addCollection(CollectionDTO collectionDTO){
        return this.itemRepository.addCollection(collectionDTO);
    }
    public Integer addCrate(CrateDTO crateDTO){
        return this.itemRepository.addCrate(crateDTO);
    }

    public List<GeneralItemDTO> getAllItems() {
        Map<String, List<Integer>> idsByType = this.itemRepository.getAllItemIds();

        Map<Integer, GeneralItemDTO> itemsById = new LinkedHashMap<>();
        itemsById.putAll(weaponRepository.getWeaponsById(idsByType.getOrDefault("weapon", new ArrayList<>())));

        List<Integer> allIds = new ArrayList<>(itemsById.keySet());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(allIds);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(allIds);

        for (GeneralItemDTO item : itemsById.values()) {
            if (item instanceof WeaponDTO weapon) {
                weapon.setCollection(collectionMap.get(weapon.getId()));
                weapon.setCrates(crateMap.get(weapon.getId()));
                weapon.setLinks(marketLinkService.generateMarketLinks(weapon));
            }
        }

        return new ArrayList<>(itemsById.values());
    }
    public List<GeneralItemDTO> getItemsByCollection(Integer collectionId) {
        List<GeneralItemDTO> items = this.itemRepository.getItemsByCollection(collectionId);
        List<Integer> ids = items.stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : items) {
            if (item instanceof WeaponDTO weapon) {
                weapon.setCollection(collectionMap.get(weapon.getId()));
                weapon.setCrates(crateMap.get(weapon.getId()));
                weapon.setLinks(marketLinkService.generateMarketLinks(weapon));
            }
        }
        return items;
    }

    public List<GeneralItemDTO> getItemsByCrate(Integer crateId) {
        List<GeneralItemDTO> items = this.itemRepository.getItemsByCrate(crateId);
        List<Integer> ids = items.stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : items) {
            if (item instanceof WeaponDTO weapon) {
                weapon.setCollection(collectionMap.get(weapon.getId()));
                weapon.setCrates(crateMap.get(weapon.getId()));
                weapon.setLinks(marketLinkService.generateMarketLinks(weapon));
            }
        }
        return items;
    }

    public GeneralItemDTO getItemById(Integer id) {
        GeneralItemDTO item = this.itemRepository.getItemById(id);
        if (item instanceof WeaponDTO weapon) {
            weapon.setCollection(itemRepository.getCollection(id));
            weapon.setCrates(itemRepository.getCrates(id));
            weapon.setLinks(marketLinkService.generateMarketLinks(weapon));
        }
        return item;
    }
    public CollectionDTO getCollectionById(Integer id) {
        return this.itemRepository.getCollectionById(id);
    }
    public CrateDTO getCrateById(Integer id) {
        return this.itemRepository.getCrateById(id);
    }
}
