package com.example.csgoskinsbackend.models.DTOs.marketLinks;

public class MarketLinkWeaponDTO extends MarketLinkDTO {
    private String wear;
    private Boolean stattrak;
    private Boolean souvenir;

    public MarketLinkWeaponDTO() {}
    public MarketLinkWeaponDTO(String wear, Boolean statrak, Boolean souvenir) {
        this.wear = wear;
        this.stattrak = statrak;
        this.souvenir = souvenir;
    }

    public String getWear() {
        return wear;
    }

    public void setWear(String wear) {
        this.wear = wear;
    }

    public Boolean getStattrak() {
        return stattrak;
    }

    public void setStattrak(Boolean stattrak) {
        this.stattrak = stattrak;
    }

    public Boolean getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(Boolean souvenir) {
        this.souvenir = souvenir;
    }
}
