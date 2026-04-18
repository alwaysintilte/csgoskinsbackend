package models.DTOs;

import java.time.LocalDate;

public class CrateDTO {
    private String name;
    private String imageUrl;
    private LocalDate dateAdded;

    public CrateDTO() {}

    public CrateDTO(String name, String imageUrl, LocalDate dateAdded) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.dateAdded = dateAdded;
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
