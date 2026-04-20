package com.example.csgoskinsbackend.models.records;

import jakarta.persistence.*;

public class GeneralItem {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private String image;

    public GeneralItem() {}

    public GeneralItem(Integer id, String name, String description, String type, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.image = image;
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
}
