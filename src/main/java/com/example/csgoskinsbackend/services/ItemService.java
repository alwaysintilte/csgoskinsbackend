package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.PagedResponseDTO;
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
    private static final Integer PAGE_SIZE = 50;
    private final ItemRepository itemRepository;
    private final MarketLinkService marketLinkService;
    @Autowired
    public ItemService(ItemRepository itemRepository, MarketLinkService marketLinkService){
        this.itemRepository = itemRepository;
        this.marketLinkService = marketLinkService;
    }
    public Integer addCollection(CollectionDTO collectionDTO){
        return this.itemRepository.addCollection(collectionDTO);
    }
    public Integer addCrate(CrateDTO crateDTO){
        return this.itemRepository.addCrate(crateDTO);
    }

    public PagedResponseDTO getAllItems() {
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
        PagedResponseDTO pagedResponseDTO = new PagedResponseDTO();
        pagedResponseDTO.setItems(new ArrayList<>(itemsById.values()));
        pagedResponseDTO.setCurrentPage(0);
        pagedResponseDTO.setTotalItems(500);
        pagedResponseDTO.setTotalPages(10);
        return pagedResponseDTO;
    }
    public PagedResponseDTO getItemsByCollection(Integer collectionId, Integer page) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.getItemsByCollection(collectionId, page);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
        }
        return pagedResponseDTO;
    }

    public PagedResponseDTO getItemsByCrate(Integer crateId, Integer page) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.getItemsByCrate(crateId, page);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
        }
        return pagedResponseDTO;
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
    public PagedResponseDTO getCollectionsByType(String type, Integer page) {
        return this.itemRepository.getCollectionsByType(type, page);
    }
    public PagedResponseDTO getCratesByType(String type, Integer page) {
        return this.itemRepository.getCratesByType(type, page);
    }
    public PagedResponseDTO getItemsByTable(String typeTable, Integer page) {
        return this.itemRepository.getItemsByTable(typeTable, page);
    }
}
