package com.example.csgoskinsbackend.models.DTOs;

import java.util.List;

public class WeaponDTO {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String weapon;
    private String category;
    private Double minFloat;
    private Double maxFloat;
    private String rarity;
    private Boolean stattrak;
    private Boolean souvenir;
    private String paintIndex;
    private List<String> wears;
    private List<CollectionDTO> collections;
    private List<CrateDTO> crates;
    private List<MarketLinkDTO> links;

    public WeaponDTO() {}

    public WeaponDTO(String name, String description, String image, String weapon, String category, Double minFloat, Double maxFloat, String rarity, Boolean stattrak, Boolean souvenir, String paintIndex, List<String> wears, List<CollectionDTO> collections, List<CrateDTO> crates, List<MarketLinkDTO> links) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.weapon = weapon;
        this.category = category;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.rarity = rarity;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
        this.paintIndex = paintIndex;
        this.wears = wears;
        this.collections = collections;
        this.crates = crates;
        this.links = links;
    }
    public WeaponDTO(Integer id, String name, String description, String image, String weapon, String category, Double minFloat, Double maxFloat, String rarity, Boolean stattrak, Boolean souvenir, String paintIndex, List<String> wears, List<CollectionDTO> collections, List<CrateDTO> crates, List<MarketLinkDTO> links) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.weapon = weapon;
        this.category = category;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.rarity = rarity;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
        this.paintIndex = paintIndex;
        this.wears = wears;
        this.collections = collections;
        this.crates = crates;
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinFloat() {
        return minFloat;
    }

    public void setMinFloat(Double minFloat) {
        this.minFloat = minFloat;
    }

    public Double getMaxFloat() {
        return maxFloat;
    }

    public void setMaxFloat(Double maxFloat) {
        this.maxFloat = maxFloat;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Boolean getStattrak() {
        return stattrak;
    }

    public void setStattrak(Boolean stattrak) {
        this.stattrak = stattrak;
    }

    public Boolean getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(Boolean souvenir) {
        this.souvenir = souvenir;
    }

    public String getPaintIndex() {
        return paintIndex;
    }

    public void setPaintIndex(String paintIndex) {
        this.paintIndex = paintIndex;
    }

    public List<String> getWears() {
        return wears;
    }

    public void setWears(List<String> wears) {
        this.wears = wears;
    }

    public List<CollectionDTO> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionDTO> collections) {
        this.collections = collections;
    }

    public List<CrateDTO> getCrates() {
        return crates;
    }

    public void setCrates(List<CrateDTO> crates) {
        this.crates = crates;
    }

    public List<MarketLinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<MarketLinkDTO> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "WeaponDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", weapon='" + weapon + '\'' +
                ", category='" + category + '\'' +
                ", minFloat=" + minFloat +
                ", maxFloat=" + maxFloat +
                ", rarity='" + rarity + '\'' +
                ", stattrak=" + stattrak +
                ", souvenir=" + souvenir +
                ", paintIndex='" + paintIndex + '\'' +
                ", wears=" + wears.toString() +
                ", collections=" + collections.toString() +
                ", crates=" + crates.toString() +
                ", links=" + links.toString() +
                '}';
    }
}
