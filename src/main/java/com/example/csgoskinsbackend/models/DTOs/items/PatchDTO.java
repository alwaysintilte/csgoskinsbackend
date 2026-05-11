package com.example.csgoskinsbackend.models.DTOs.items;

public class PatchDTO extends GeneralItemDTO {
    private Integer defIndex;

    public PatchDTO() {}

    public PatchDTO(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }
}