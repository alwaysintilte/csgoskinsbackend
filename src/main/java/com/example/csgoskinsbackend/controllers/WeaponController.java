package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.models.DTOs.WeaponDTO;
import com.example.csgoskinsbackend.services.ItemService;
import com.example.csgoskinsbackend.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    @Autowired
    public WeaponController(WeaponService weaponService){
        this.weaponService = weaponService;
    }

    @GetMapping("/all")
    public List<WeaponDTO> getAllWeapons(){
        return weaponService.getAllWeapons();
    }
    @PostMapping("/save")
    public void saveWeapon(@RequestBody WeaponDTO weaponDTO){
        weaponService.saveWeapon(weaponDTO);
    }
}
