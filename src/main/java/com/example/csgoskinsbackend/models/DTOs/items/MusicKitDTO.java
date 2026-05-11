package com.example.csgoskinsbackend.models.DTOs.items;

public class MusicKitDTO extends GeneralItemDTO {
    private Integer defIndex;
    private Boolean exclusive;

    public MusicKitDTO() {}

    public MusicKitDTO(Integer defIndex, Boolean exclusive) {
        this.defIndex = defIndex;
        this.exclusive = exclusive;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }
}