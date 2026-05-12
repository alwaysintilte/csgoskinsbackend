package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.models.DTOs.CollectionDTO;
import com.example.csgoskinsbackend.models.DTOs.CrateDTO;
import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.csgoskinsbackend.services.ItemService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    @PostMapping("/add/collection")
    public Integer addCollection(@RequestBody CollectionDTO collectionDTO){
        return this.itemService.addCollection(collectionDTO);
    }
    @PostMapping("/add/crate")
    public Integer addCrate(@RequestBody CrateDTO crateDTO){
        return this.itemService.addCrate(crateDTO);
    }

    @GetMapping("/all")
    public List<GeneralItemDTO> getAllItems(){
        return this.itemService.getAllItems();
    }

    @GetMapping("/item/{id}")
    public GeneralItemDTO getItemById(@PathVariable Integer id){
        return this.itemService.getItemById(id);
    }

    @GetMapping("/collection/{collectionId}")
    public List<GeneralItemDTO> getItemsByCollection(@PathVariable Integer collectionId){
        return this.itemService.getItemsByCollection(collectionId);
    }

    @GetMapping("/collection/{id}/info")
    public CollectionDTO getCollectionById(@PathVariable Integer id) {
        return this.itemService.getCollectionById(id);
    }

    @GetMapping("/crate/{crateId}")
    public List<GeneralItemDTO> getItemsByCrate(@PathVariable Integer crateId){
        return this.itemService.getItemsByCrate(crateId);
    }

    @GetMapping("/crate/{id}/info")
    public CrateDTO getCrateById(@PathVariable Integer id) {
        return this.itemService.getCrateById(id);
    }

    @GetMapping("/search/name/{name}")
    public List<GeneralItemDTO> getItemsByName(@PathVariable String name){
        return this.itemService.getItemsByName(name);
    }
}
