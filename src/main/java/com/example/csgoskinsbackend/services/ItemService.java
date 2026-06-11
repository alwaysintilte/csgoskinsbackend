package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.ItemSearchFilters;
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
    private final PriceService priceService;
    private final MarketLinkService marketLinkService;
    @Autowired
    public ItemService(ItemRepository itemRepository, PriceService priceService, MarketLinkService marketLinkService){
        this.itemRepository = itemRepository;
        this.priceService = priceService;
        this.marketLinkService = marketLinkService;
    }
    public Integer addCollection(CollectionDTO collectionDTO){
        return this.itemRepository.addCollection(collectionDTO);
    }
    public Integer addCrate(CrateDTO crateDTO){
        return this.itemRepository.addCrate(crateDTO);
    }

    public PagedResponseDTO getAllItems(Integer page) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.getAllItems(page);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
            item.setPrices(this.priceService.generatePrices(item));
        }
        return pagedResponseDTO;
    }
    public PagedResponseDTO searchAllItemsByName(Integer page, String name) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.searchAllItemsByName(page, name);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
            item.setPrices(this.priceService.generatePrices(item));
        }
        return pagedResponseDTO;
    }
    public PagedResponseDTO searchAllItemsByFilters(Integer page, ItemSearchFilters itemSearchFilters) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.searchAllItemsByFilters(page, itemSearchFilters);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
            item.setPrices(this.priceService.generatePrices(item));
        }
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
            item.setPrices(this.priceService.generatePrices(item));
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
            item.setPrices(this.priceService.generatePrices(item));
        }
        return pagedResponseDTO;
    }

    public GeneralItemDTO getItemById(Integer id) {
        GeneralItemDTO item = this.itemRepository.getItemById(id);
        item.setCollection(itemRepository.getCollection(id));
        item.setCrates(itemRepository.getCrates(id));
        item.setLinks(marketLinkService.generateMarketLinks(item));
        item.setPrices(this.priceService.generatePrices(item));
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
        PagedResponseDTO pagedResponseDTO = this.itemRepository.getCratesByType(type, page);
        for (GeneralItemDTO item: pagedResponseDTO.getItems()) {
            item.setPrices(this.priceService.generatePrices(item));
        }
        return pagedResponseDTO;
    }
    public PagedResponseDTO getItemsByTable(String typeTable, Integer page) {
        PagedResponseDTO pagedResponseDTO = this.itemRepository.getItemsByTable(typeTable, page);
        List<Integer> ids = pagedResponseDTO.getItems().stream().map(GeneralItemDTO::getId).collect(Collectors.toList());
        Map<Integer, CollectionDTO> collectionMap = this.itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = this.itemRepository.getAllCrates(ids);
        for (GeneralItemDTO item : pagedResponseDTO.getItems()) {
            item.setCollection(collectionMap.get(item.getId()));
            item.setCrates(crateMap.get(item.getId()));
            item.setLinks(marketLinkService.generateMarketLinks(item));
            item.setPrices(this.priceService.generatePrices(item));
        }
        return pagedResponseDTO;
    }
}
