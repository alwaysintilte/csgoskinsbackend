package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.*;
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
    @Autowired
    public WeaponService(ItemRepository itemRepository, WeaponRepository weaponRepository){
        this.itemRepository = itemRepository;
        this.weaponRepository = weaponRepository;
    }
    public List<WeaponDTO> getAllWeapons(){
        List<WeaponDTO> weapons = weaponRepository.getAllWeapons();
        List<Integer> ids = weapons.stream().map(weapon -> weapon.getId()).collect(Collectors.toList());
        Map<Integer, List<CollectionDTO>> collectionMap = itemRepository.getAllCollections(ids);
        Map<Integer, List<CrateDTO>> crateMap = itemRepository.getAllCrates(ids);
        Map<Integer, List<MarketLinkDTO>> marketLinkMap = itemRepository.getAllMarketLinks(ids);
        for (WeaponDTO weapon : weapons) {
            weapon.setCollections(collectionMap.get(weapon.getId()));
            weapon.setCrates(crateMap.get(weapon.getId()));
            weapon.setLinks(marketLinkMap.get(weapon.getId()));
            System.out.println(weapon.toString());
        }
        return weapons;
    }
    public Integer saveWeapon(WeaponDTO weaponDTO){
        Integer generalItemId = itemRepository.addItem(new GeneralItemDTO(weaponDTO.getName(), weaponDTO.getDescription(), "weapon", weaponDTO.getImage()));
        weaponDTO.setId(generalItemId);
        weaponRepository.addWeapon(weaponDTO);
        return generalItemId;
    }
}
