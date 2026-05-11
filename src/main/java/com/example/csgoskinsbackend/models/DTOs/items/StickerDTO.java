package com.example.csgoskinsbackend.models.DTOs.items;

public class StickerDTO extends GeneralItemDTO {
    private Integer defIndex;
    private String stickerType;
    private String effect;
    private String tournament;
    private String team;
    private String player;

    public StickerDTO() {}

    public StickerDTO(Integer defIndex, String stickerType, String effect, String tournament, String team, String player) {
        this.defIndex = defIndex;
        this.stickerType = stickerType;
        this.effect = effect;
        this.tournament = tournament;
        this.team = team;
        this.player = player;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public String getStickerType() {
        return stickerType;
    }

    public void setStickerType(String stickerType) {
        this.stickerType = stickerType;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
