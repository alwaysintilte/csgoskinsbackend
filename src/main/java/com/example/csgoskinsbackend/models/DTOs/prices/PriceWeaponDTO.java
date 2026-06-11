package com.example.csgoskinsbackend.models.DTOs.prices;

public class PriceWeaponDTO extends PriceDTO {
    private String wear;
    private Boolean stattrak;
    private Boolean souvenir;
    public PriceWeaponDTO() {}

    public PriceWeaponDTO(String wear, Boolean stattrak, Boolean souvenir, Integer price) {
        super(price);
        this.wear = wear;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
    }

    public String getWear() { return wear; }
    public void setWear(String wear) { this.wear = wear; }
    public Boolean getStattrak() { return stattrak; }
    public void setStattrak(Boolean stattrak) { this.stattrak = stattrak; }
    public Boolean getSouvenir() { return souvenir; }
    public void setSouvenir(Boolean souvenir) { this.souvenir = souvenir; }
}
