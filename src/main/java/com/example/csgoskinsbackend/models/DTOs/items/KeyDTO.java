package com.example.csgoskinsbackend.models.DTOs.items;

public class KeyDTO extends GeneralItemDTO {
    private Integer defIndex;
    private Boolean marketable;

    public KeyDTO() {}

    public KeyDTO(Integer defIndex, Boolean marketable) {
        this.defIndex = defIndex;
        this.marketable = marketable;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public Boolean getMarketable() {
        return marketable;
    }

    public void setMarketable(Boolean marketable) {
        this.marketable = marketable;
    }
}