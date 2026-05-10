package com.example.csgoskinsbackend.models.DTOs;

public class MarketLinkWeaponDTO extends MarketLinkDTO {
    private String wear;
    private Boolean statrak;
    private Boolean souvenir;

    public MarketLinkWeaponDTO(String wear, Boolean statrak, Boolean souvenir) {
        this.wear = wear;
        this.statrak = statrak;
        this.souvenir = souvenir;
    }

    public String getWear() {
        return wear;
    }

    public void setWear(String wear) {
        this.wear = wear;
    }

    public Boolean getStatrak() {
        return statrak;
    }

    public void setStatrak(Boolean statrak) {
        this.statrak = statrak;
    }

    public Boolean getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(Boolean souvenir) {
        this.souvenir = souvenir;
    }
}
