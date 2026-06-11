package com.example.csgoskinsbackend.services;

import com.example.csgoskinsbackend.models.DTOs.items.*;
import com.example.csgoskinsbackend.models.DTOs.prices.*;
import com.example.csgoskinsbackend.repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceDTO> generatePrices(GeneralItemDTO item) {
        if (item instanceof WeaponDTO weapon) {
            return generateWeaponPrices(weapon);
        } else if (item instanceof StickerDTO sticker) {
            return generateStickerPrices(sticker);
        } else if (item instanceof AgentDTO agent) {
            return generateAgentPrices(agent);
        } else if (item instanceof CollectibleDTO collectible) {
            return generateCollectiblePrices(collectible);
        } else if (item instanceof GraffitiDTO graffiti) {
            return generateGraffitiPrices(graffiti);
        } else if (item instanceof KeychainDTO keychain) {
            return generateKeychainPrices(keychain);
        } else if (item instanceof KeyDTO key) {
            return generateKeyPrices(key);
        } else if (item instanceof MusicKitDTO musicKit) {
            return generateMusicKitPrices(musicKit);
        } else if (item instanceof PatchDTO patch) {
            return generatePatchPrices(patch);
        }
        return new ArrayList<>();
    }

    private List<PriceDTO> generateWeaponPrices(WeaponDTO weapon) {
        List<PriceDTO> result = new ArrayList<>();
        List<Object> keys = new ArrayList<>();

        if (weapon.getWears() == null || weapon.getWears().isEmpty()) {
            keys.add(weapon.getName());
            if (Boolean.TRUE.equals(weapon.getStattrak())) {
                keys.add("StatTrak™ " + weapon.getName());
            }
        } else {
            for (String wear : weapon.getWears()) {
                keys.add(weapon.getName() + " (" + wear + ")");
            }
            if (Boolean.TRUE.equals(weapon.getStattrak())) {
                for (String wear : weapon.getWears()) {
                    keys.add("StatTrak™ " + weapon.getName() + " (" + wear + ")");
                }
            }
            if (Boolean.TRUE.equals(weapon.getSouvenir())) {
                for (String wear : weapon.getWears()) {
                    keys.add("Souvenir " + weapon.getName() + " (" + wear + ")");
                }
            }
        }

        List<Object> values = priceRepository.getPricesByKeys(keys);

        if (weapon.getWears() == null || weapon.getWears().isEmpty()) {
            result.add(new PriceWeaponDTO(null, false, false, parsePrice(values.get(0))));
            if (Boolean.TRUE.equals(weapon.getStattrak())) {
                result.add(new PriceWeaponDTO(null, true, false, parsePrice(values.get(1))));
            }
        } else {
            int index = 0;
            for (String wear : weapon.getWears()) {
                result.add(new PriceWeaponDTO(wear, false, false, parsePrice(values.get(index++))));
            }
            if (Boolean.TRUE.equals(weapon.getStattrak())) {
                for (String wear : weapon.getWears()) {
                    result.add(new PriceWeaponDTO(wear, true, false, parsePrice(values.get(index++))));
                }
            }
            if (Boolean.TRUE.equals(weapon.getSouvenir())) {
                for (String wear : weapon.getWears()) {
                    result.add(new PriceWeaponDTO(wear, false, true, parsePrice(values.get(index++))));
                }
            }
        }
        return result;
    }

    private List<PriceDTO> generateStickerPrices(StickerDTO sticker) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(sticker.getName());
        result.add(new PriceStickerDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateAgentPrices(AgentDTO agent) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(agent.getName());
        result.add(new PriceAgentDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateCollectiblePrices(CollectibleDTO collectible) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(collectible.getName());
        result.add(new PriceCollectibleDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateGraffitiPrices(GraffitiDTO graffiti) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(graffiti.getName());
        result.add(new PriceGraffitiDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateKeychainPrices(KeychainDTO keychain) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(keychain.getName());
        result.add(new PriceKeychainDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateKeyPrices(KeyDTO key) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(key.getName());
        result.add(new PriceKeyDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generateMusicKitPrices(MusicKitDTO musicKit) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(musicKit.getName());
        result.add(new PriceMusicKitDTO(parsePrice(value)));
        return result;
    }

    private List<PriceDTO> generatePatchPrices(PatchDTO patch) {
        List<PriceDTO> result = new ArrayList<>();
        Object value = priceRepository.getPriceByKey(patch.getName());
        result.add(new PricePatchDTO(parsePrice(value)));
        return result;
    }

    private Integer parsePrice(Object value) {
        System.out.println(value);
        return value != null ? Integer.parseInt(value.toString()) : null;
    }
}
