package com.example.csgoskinsbackend.models.DTOs;

public class ItemSearchFilters {
    private String name;
    private String type;
    private String rarity;
    private String weapon;
    private String wear;
    private Double minFloat;
    private Double maxFloat;
    private String quality;
    private String stickerType;
    private String tournament;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRarity() { return rarity; }
    public void setRarity(String rarity) { this.rarity = rarity; }

    public String getWeapon() { return weapon; }
    public void setWeapon(String weapon) { this.weapon = weapon; }

    public String getWear() { return wear; }
    public void setWear(String wear) { this.wear = wear; }

    public Double getMinFloat() { return minFloat; }
    public void setMinFloat(Double minFloat) { this.minFloat = minFloat; }

    public Double getMaxFloat() { return maxFloat; }
    public void setMaxFloat(Double maxFloat) { this.maxFloat = maxFloat; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }

    public String getStickerType() { return stickerType; }
    public void setStickerType(String stickerType) { this.stickerType = stickerType; }

    public String getTournament() { return tournament; }
    public void setTournament(String tournament) { this.tournament = tournament; }

    public Boolean isStatTrak() {
        return "stattrak".equalsIgnoreCase(quality) ? true : null;
    }

    public Boolean isSouvenir() {
        return "souvenir".equalsIgnoreCase(quality) ? true : null;
    }
}
