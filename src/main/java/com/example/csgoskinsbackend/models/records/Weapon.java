package com.example.csgoskinsbackend.models.records;

import jakarta.persistence.*;

import java.util.List;

public class Weapon {
    private Integer id;
    private String weapon;
    private String category;
    private Double minFloat;
    private Double maxFloat;
    private String rarity;
    private Boolean stattrak;
    private Boolean souvenir;
    private Integer paintIndex;
    private List<String> wears;

    public Weapon() {}

    public Weapon(Integer id, String weapon, String category, Double minFloat, Double maxFloat, String rarity, Boolean stattrak, Boolean souvenir, Integer paintIndex, List<String> wears) {
        this.id = id;
        this.weapon = weapon;
        this.category = category;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.rarity = rarity;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
        this.paintIndex = paintIndex;
        this.wears = wears;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPaintIndex() {
        return paintIndex;
    }

    public void setPaintIndex(Integer paintIndex) {
        this.paintIndex = paintIndex;
    }

    public List<String> getWears() {
        return wears;
    }

    public void setWears(List<String> wears) {
        this.wears = wears;
    }
}
