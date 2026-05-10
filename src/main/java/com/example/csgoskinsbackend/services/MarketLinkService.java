package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.MarketLinkDTO;
import com.example.csgoskinsbackend.models.DTOs.GeneralItemDTO;
import com.example.csgoskinsbackend.models.DTOs.MarketLinkWeaponDTO;
import com.example.csgoskinsbackend.models.DTOs.WeaponDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketLinkService {
    private static final String BASE_URL = "https://steamcommunity.com/market/listings/730/";
    public List<MarketLinkDTO> generateMarketLinks(GeneralItemDTO item) {
        if (item instanceof WeaponDTO weapon) {
            return generateWeaponLinks(weapon);
        }
        return new ArrayList<>();
    }
    private List<MarketLinkDTO> generateWeaponLinks(WeaponDTO weapon) {
        List<MarketLinkDTO> result = new ArrayList<>();
        for (String wear : weapon.getWears()) {
            MarketLinkWeaponDTO dto = new MarketLinkWeaponDTO(wear, false, false);
            String fullName = weapon.getName() + " (" + wear + ")";
            String encodedLink = encodeUrl(fullName);
            String finalUrl = BASE_URL + encodedLink;
            dto.setLink(finalUrl);
            result.add(dto);
        }
        if (weapon.getStattrak()) {
            for (String wear : weapon.getWears()) {
                MarketLinkWeaponDTO dto = new MarketLinkWeaponDTO(wear, true, false);
                String prefix = "StatTrak™ ";
                String fullName = prefix + weapon.getName() + " (" + wear + ")";
                String encodedLink = encodeUrl(fullName);
                String finalUrl = BASE_URL + encodedLink;
                dto.setLink(finalUrl);
                result.add(dto);
            }
        }
        if (weapon.getSouvenir()) {
            for (String wear : weapon.getWears()) {
                MarketLinkWeaponDTO dto = new MarketLinkWeaponDTO(wear, false, true);
                String prefix = "Souvenir ";
                String fullName = prefix + weapon.getName() + " (" + wear + ")";
                String encodedLink = encodeUrl(fullName);
                String finalUrl = BASE_URL + encodedLink;
                dto.setLink(finalUrl);
                result.add(dto);
            }
        }
        return result;
    }
    private String encodeUrl(String string){
        return string.replace(" ", "%20")
                .replace("|", "%7C")
                .replace("(", "%28")
                .replace(")", "%29");
    }
}
