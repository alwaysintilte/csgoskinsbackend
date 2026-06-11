package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.items.*;
import com.example.csgoskinsbackend.models.DTOs.marketLinks.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarketLinkService {
    private static final String BASE_URL = "https://steamcommunity.com/market/listings/730/";
    public List<MarketLinkDTO> generateMarketLinks(GeneralItemDTO item) {
        if (item instanceof AgentDTO agent) {
            return generateAgentLinks(agent);
        }
        else if (item instanceof CollectibleDTO collectible) {
            return generateCollectibleLinks(collectible);
        }
        else if (item instanceof GraffitiDTO graffiti) {
            return generateGraffitiLinks(graffiti);
        }
        else if (item instanceof KeychainDTO keychain) {
            return generateKeychainLinks(keychain);
        }
        else if (item instanceof KeyDTO key) {
            return generateKeyLinks(key);
        }
        else if (item instanceof MusicKitDTO musicKit) {
            return generateMusicKitLinks(musicKit);
        }
        else if (item instanceof PatchDTO patch) {
            return generatePatchLinks(patch);
        }
        else if (item instanceof WeaponDTO weapon) {
            return generateWeaponLinks(weapon);
        }
        else if (item instanceof StickerDTO sticker) {
            return generateStickerLinks(sticker);
        }
        return new ArrayList<>();
    }
    private List<MarketLinkDTO> generateAgentLinks(AgentDTO agentDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkAgentDTO();
        String fullName = agentDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generateCollectibleLinks(CollectibleDTO collectibleDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkCollectibleDTO();
        String fullName = collectibleDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generateGraffitiLinks(GraffitiDTO graffitiDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkGraffitiDTO();
        String fullName = graffitiDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generateKeychainLinks(KeychainDTO keychainDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkKeychainDTO();
        String fullName = keychainDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generateKeyLinks(KeyDTO keyDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkKeyDTO();
        String fullName = keyDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generateMusicKitLinks(MusicKitDTO musicKitDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkMusicKitDTO();
        String fullName = musicKitDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private List<MarketLinkDTO> generatePatchLinks(PatchDTO patchDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkPatchDTO();
        String fullName = patchDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
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
        if(weapon.getWears().isEmpty() || weapon.getWears()==null){
            MarketLinkDTO dto = new MarketLinkWeaponDTO(null, false, false);
            String fullName = weapon.getName();
            String encodedLink = encodeUrl(fullName);
            String finalUrl = BASE_URL + encodedLink;
            dto.setLink(finalUrl);
            result.add(dto);
        }
        if((weapon.getWears().isEmpty() || weapon.getWears()==null)&&(weapon.getStattrak())){
            MarketLinkDTO dto = new MarketLinkWeaponDTO(null, true, false);
            String prefix = "StatTrak™ ";
            String fullName = prefix + weapon.getName();
            String encodedLink = encodeUrl(fullName);
            String finalUrl = BASE_URL + encodedLink;
            dto.setLink(finalUrl);
            result.add(dto);
        }
        return result;
    }
    private List<MarketLinkDTO> generateStickerLinks(StickerDTO stickerDTO) {
        List<MarketLinkDTO> result = new ArrayList<>();
        MarketLinkDTO dto = new MarketLinkStickerDTO();
        String fullName = stickerDTO.getName();
        String encodedLink = encodeUrl(fullName);
        String finalUrl = BASE_URL + encodedLink;
        dto.setLink(finalUrl);
        result.add(dto);
        return result;
    }
    private String encodeUrl(String string){
        return string.replace(" ", "%20")
                .replace("|", "%7C")
                .replace("(", "%28")
                .replace(")", "%29");
    }
}
