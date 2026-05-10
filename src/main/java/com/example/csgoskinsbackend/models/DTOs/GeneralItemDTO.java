package com.example.csgoskinsbackend.models.DTOs;

import java.util.List;

public abstract class GeneralItemDTO {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String image;
    private String rarity;
    private CollectionDTO collection;
    private List<CrateDTO> crates;
    private List<MarketLinkDTO> links;

    public GeneralItemDTO() {}

    public GeneralItemDTO(Integer id, String name, String description, String type, String image, String rarity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
        this.rarity = rarity;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public CollectionDTO getCollection() {
        return collection;
    }

    public void setCollection(CollectionDTO collection) {
        this.collection = collection;
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + type + '\'' +
                ", image='" + image + '\'' +
                ", image='" + rarity + '\'' +
                '}';
    }
}
