package com.example.csgoskinsbackend.models.DTOs;

import com.example.csgoskinsbackend.models.DTOs.items.GeneralItemDTO;

import java.util.List;

public class PagedResponseDTO {
    private List<GeneralItemDTO> items;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalItems;

    public PagedResponseDTO(List<GeneralItemDTO> items, Integer currentPage, Integer totalPages, Integer totalItems) {
        this.items = items;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<GeneralItemDTO> getItems() {
        return items;
    }

    public void setItems(List<GeneralItemDTO> items) {
        this.items = items;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }
}
