package com.example.csgoskinsbackend.models.DTOs.items;

public class AgentDTO extends GeneralItemDTO {
    private Integer defIndex;
    private String team;

    public AgentDTO() {}

    public AgentDTO(Integer defIndex, String team) {
        this.defIndex = defIndex;
        this.team = team;
    }

    public Integer getDefIndex() {
        return defIndex;
    }

    public void setDefIndex(Integer defIndex) {
        this.defIndex = defIndex;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
