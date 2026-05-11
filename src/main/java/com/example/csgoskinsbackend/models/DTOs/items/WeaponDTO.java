package com.example.csgoskinsbackend.models.DTOs.items;

import java.util.List;

public class WeaponDTO extends GeneralItemDTO {
    private String weapon;
    private String category;
    private Double minFloat;
    private Double maxFloat;
    private Boolean stattrak;
    private Boolean souvenir;
    private String paintIndex;
    private List<String> wears;

    public WeaponDTO() {}

    public WeaponDTO(String weapon, String category, Double minFloat, Double maxFloat, Boolean stattrak, Boolean souvenir, String paintIndex, List<String> wears) {
        this.weapon = weapon;
        this.category = category;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
        this.paintIndex = paintIndex;
        this.wears = wears;
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
}
