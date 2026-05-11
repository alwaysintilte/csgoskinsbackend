package com.example.csgoskinsbackend.models.DTOs.items;

public class CollectibleDTO extends GeneralItemDTO {
    private Integer defIndex;
    private String collectibleType;
    private Boolean genuine;

    public CollectibleDTO() {}

    public CollectibleDTO(Integer defIndex, String collectibleType, Boolean genuine) {
        this.defIndex = defIndex;
        this.collectibleType = collectibleType;
        this.genuine = genuine;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public String getCollectibleType() {
        return collectibleType;
    }

    public void setCollectibleType(String collectibleType) {
        this.collectibleType = collectibleType;
    }

    public Boolean getGenuine() {
        return genuine;
    }

    public void setGenuine(Boolean genuine) {
        this.genuine = genuine;
    }
}
