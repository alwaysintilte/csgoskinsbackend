package com.example.csgoskinsbackend.models.DTOs.marketLinks;

public abstract class MarketLinkDTO {
    private String link;

    public MarketLinkDTO() {}

    public MarketLinkDTO(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    @Override
    public String toString() {
        return "Link{link='" + link + "'}";
    }
}
