package com.example.csgoskinsbackend.models.DTOs;

import java.util.List;

public class GeneralItemDTO {
    private String name;
    private String description;
    private String type;
    private String image;

    public GeneralItemDTO() {}

    public GeneralItemDTO(String name, String description, String type, String image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
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

    @Override
    public String toString() {
        return "WeaponDTO{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + type + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
