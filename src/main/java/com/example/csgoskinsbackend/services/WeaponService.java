package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.*;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.repositories.ItemRepository;
import com.example.csgoskinsbackend.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeaponService {
    private final ItemRepository itemRepository;
    private final WeaponRepository weaponRepository;
    private final MarketLinkService marketLinkService;
    @Autowired
    public WeaponService(ItemRepository itemRepository, WeaponRepository weaponRepository, MarketLinkService marketLinkService){
        this.itemRepository = itemRepository;
        this.weaponRepository = weaponRepository;
        this.marketLinkService = marketLinkService;
    }
    public List<GeneralItemDTO> getItemsByWeaponType(String weapon) {
        List<GeneralItemDTO> items = this.weaponRepository.getItemsByWeaponType(weapon);
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
    public List<GeneralItemDTO> getItemsByCategoryType(String category) {
        List<GeneralItemDTO> items = this.weaponRepository.getItemsByCategoryType(category);
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
//    public List<GeneralItemDTO> getAllWeapons(){
//        List<GeneralItemDTO> weapons = weaponRepository.getAllWeapons();
//        List<Integer> ids = weapons.stream().map(weapon -> weapon.getId()).collect(Collectors.toList());
//        Map<Integer, CollectionDTO> collectionMap = itemRepository.getAllCollections(ids);
//        Map<Integer, List<CrateDTO>> crateMap = itemRepository.getAllCrates(ids);
//        for (GeneralItemDTO item : weapons) {
//            if (item instanceof WeaponDTO weapon) {
//                weapon.setCollection(collectionMap.get(weapon.getId()));
//                weapon.setCrates(crateMap.get(weapon.getId()));
//                weapon.setLinks(marketLinkService.generateMarketLinks(weapon));
//            }
//        }
//        return weapons;
//    }
//    public WeaponDTO getWeaponById(Integer id){
//        WeaponDTO weaponDTO = weaponRepository.getWeaponById(id);
//        try {
//            CollectionDTO collectionDTO = itemRepository.getCollection(id);
//            weaponDTO.setCollection(collectionDTO);
//        } catch (Exception e){
//            weaponDTO.setCollection(null);
//        }
//        try {
//            List<CrateDTO> crateDTOS = itemRepository.getCrates(id);
//            weaponDTO.setCrates(crateDTOS);
//        } catch (Exception e){
//            weaponDTO.setCrates(null);
//        }
//        List<MarketLinkDTO> marketLinkDTOS = marketLinkService.generateMarketLinks(weaponDTO);
//        weaponDTO.setLinks(marketLinkDTOS);
//        return weaponDTO;
//    }
//    public Integer saveWeapon(WeaponDTO weaponDTO){
//        Integer generalItemId = itemRepository.addItem(new GeneralItemDTO(weaponDTO.getName(), weaponDTO.getDescription(), "weapon", weaponDTO.getImage(), weaponDTO.getRarity()));
//        weaponDTO.setId(generalItemId);
//        weaponRepository.addWeapon(weaponDTO);
//        return generalItemId;
//    }
}
