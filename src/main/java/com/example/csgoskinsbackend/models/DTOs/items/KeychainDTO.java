package com.example.csgoskinsbackend.models.DTOs.items;

public class KeychainDTO extends GeneralItemDTO {
    private Integer defIndex;

    public KeychainDTO() {}

    public KeychainDTO(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

}