package com.example.csgoskinsbackend.models.DTOs;

import java.time.LocalDate;

public class CrateDTO {
    private Integer id;
    private String name;
    private String image;
    private LocalDate dateAdded;

    public CrateDTO() {}

    public CrateDTO(Integer id, String name, String image, LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.dateAdded = dateAdded;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
    @Override
    public String toString() {
        return "Crate{name='" + name + "'}";
    }
}
