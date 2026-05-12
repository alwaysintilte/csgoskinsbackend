package com.example.csgoskinsbackend.controllers;

import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;
import com.example.csgoskinsbackend.services.StickerService;
import com.example.csgoskinsbackend.services.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/stickers")
public class StickerController {
    private final StickerService stickerService;
    @Autowired
    public StickerController(StickerService stickerService){
        this.stickerService = stickerService;
    }

    @GetMapping("/search/tournament/{tournament}")
    public List<GeneralItemDTO> getStickersByTournament(@PathVariable String tournament){
        return stickerService.getStickersByTournament(tournament);
    }
}
