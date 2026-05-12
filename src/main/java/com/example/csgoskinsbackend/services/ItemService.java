package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.csgoskinsbackend.repositories.ItemRepository;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.csgoskinsbackend.utils.TypeMapper.getTypeTable;

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
        for (Map.Entry<String, List<Integer>> entry : idsByType.entrySet()) {
            String typeTable = getTypeTable(entry.getKey());
            List<Integer> ids = entry.getValue();
            if (!ids.isEmpty()) {
                itemsById.putAll(itemRepository.getItemsByIdAndTable(ids, typeTable));
            }
        }
        List<Integer> allIds = new ArrayList<>(itemsById.keySet());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(allIds);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(allIds);
        for (GeneralItemDTO item : itemsById.values()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
        }

        return new ArrayList<>(itemsById.values());
    }
    public List<GeneralItemDTO> getItemsByCollection(Integer collectionId) {
        List<GeneralItemDTO> items = this.itemRepository.getItemsByCollection(collectionId);
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

    public List<GeneralItemDTO> getItemsByCrate(Integer crateId) {
        List<GeneralItemDTO> items = this.itemRepository.getItemsByCrate(crateId);
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

    public GeneralItemDTO getItemById(Integer id) {
        GeneralItemDTO item = this.itemRepository.getItemById(id);
        item.setCollection(itemRepository.getCollection(id));
        item.setCrates(itemRepository.getCrates(id));
        item.setLinks(marketLinkService.generateMarketLinks(item));
        return item;
    }

    public CollectionDTO getCollectionById(Integer id) {
        return this.itemRepository.getCollectionById(id);
    }
    public CrateDTO getCrateById(Integer id) {
        return this.itemRepository.getCrateById(id);
    }

    public List<GeneralItemDTO> getItemsByName(String searchName) {
        Map<String, List<Integer>> idsByType = this.itemRepository.getItemIdsByName(searchName);
        Map<Integer, GeneralItemDTO> itemsById = new LinkedHashMap<>();
        for (Map.Entry<String, List<Integer>> entry : idsByType.entrySet()) {
            String typeTable = getTypeTable(entry.getKey());
            List<Integer> ids = entry.getValue();
            if (!ids.isEmpty()) {
                itemsById.putAll(itemRepository.getItemsByIdAndTable(ids, typeTable));
            }
        }
        List<Integer> allIds = new ArrayList<>(itemsById.keySet());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(allIds);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(allIds);
        for (GeneralItemDTO item : itemsById.values()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
        }
        return new ArrayList<>(itemsById.values());
    }
}
