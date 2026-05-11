package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/weapons")
public class WeaponController {
    private final WeaponService weaponService;
    @Autowired
    public WeaponController(WeaponService weaponService){
        this.weaponService = weaponService;
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
