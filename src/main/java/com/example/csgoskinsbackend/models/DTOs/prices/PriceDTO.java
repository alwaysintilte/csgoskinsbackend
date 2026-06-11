package com.example.csgoskinsbackend.models.DTOs.prices;

public abstract class PriceDTO {
    public Integer price;
    public PriceDTO(){}
    public PriceDTO(Integer price){
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
