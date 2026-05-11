package com.example.csgoskinsbackend.models.DTOs.items;

public class GraffitiDTO extends GeneralItemDTO {
    private Integer defIndex;

    public GraffitiDTO() {}

    public GraffitiDTO(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }
}
