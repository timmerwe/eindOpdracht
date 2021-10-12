package com.example.Eindproject.dto;

import com.example.Eindproject.entity.Part;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PartDto {
    Long id;

    @NotEmpty
    String name;

    @NotNull
    @Min(1)
    int price;

    @NotNull
    @Min(1)
    int quantity;


    public PartDto(Part p) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.quantity = p.getQuantity();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
