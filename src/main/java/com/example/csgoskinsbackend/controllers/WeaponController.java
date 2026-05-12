package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    @Autowired
    public WeaponController(WeaponService weaponService){
        this.weaponService = weaponService;
    }

    @GetMapping("/search/weapon/{weapon}")
    public List<GeneralItemDTO> getItemsByWeaponType(@PathVariable String weapon){
        return weaponService.getItemsByWeaponType(weapon);
    }
    @GetMapping("/search/category/{category}")
    public List<GeneralItemDTO> getItemsByCategoryType(@PathVariable String category){
        return weaponService.getItemsByCategoryType(category);
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
