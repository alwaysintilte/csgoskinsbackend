package models.DTOs;

public class MarketLinkDTO {
    private String wear;
    private Boolean statrak;
    private Boolean souvenir;
    private String link;

    public MarketLinkDTO() {}

    public MarketLinkDTO(String wear, Boolean statrak, Boolean souvenir, String link) {
        this.wear = wear;
        this.statrak = statrak;
        this.souvenir = souvenir;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
