package com.example.csgoskinsbackend.models.records;

public class MarketLink {
    private Integer id;
    private Integer itemId;
    private String wear;
    private Boolean stattrak;
    private Boolean souvenir;
    private String link;

    public MarketLink() {}

    public MarketLink(Integer id, Integer weaponId, String wear, Boolean stattrak, Boolean souvenir, String link) {
        this.id = id;
        this.itemId = weaponId;
        this.wear = wear;
        this.stattrak = stattrak;
        this.souvenir = souvenir;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
