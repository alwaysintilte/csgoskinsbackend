package models.records;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Crate {
    private Integer id;
    private String name;
    private String imageUrl;
    private LocalDate dateAdded;

    public Crate() {}

    public Crate(Integer id, String name, String imageUrl, LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
