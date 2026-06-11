package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.models.DTOs.PagedResponseDTO;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    @Autowired
    public WeaponController(WeaponService weaponService){
        this.weaponService = weaponService;
    }

    @GetMapping("/search/weapon/{weapon}")
    public PagedResponseDTO getItemsByWeaponType(@PathVariable String weapon, @RequestParam(defaultValue = "0") Integer page){
        return weaponService.getItemsByWeaponType(weapon, page);
    }
    @GetMapping("/search/category/{category}")
    public PagedResponseDTO getItemsByCategoryType(@PathVariable String category, @RequestParam(defaultValue = "0") Integer page){
        return weaponService.getItemsByCategoryType(category, page);
    }
//    @GetMapping("/all")
//    public List<GeneralItemDTO> getAllWeapons(){
//        return weaponService.getAllWeapons();
//    }
//    @GetMapping("/weapon/{id}")
//    public GeneralItemDTO getWeaponById(@PathVariable Integer id){
//        return weaponService.getWeaponById(id);
//    }
//    @PostMapping("/add")
//    public void saveWeapon(@RequestBody WeaponDTO weaponDTO){
//        weaponService.saveWeapon(weaponDTO);
//    }
}
