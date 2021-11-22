package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarActionDto {
    Long id;

    @NotBlank(message = "De handeling moet een titel bevatten")
    String title;

    @NotBlank(message = "De handeling moet een beschrijving bevatten")
    String description;

    double price;

    public CarActionDto(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
