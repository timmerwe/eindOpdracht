package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarPartDto {
    Long id;

    @NotBlank(message = "Het onderdeel moet een naam bevatten")
    String name;

    double price;

    @NotBlank(message = "Het onderdeel moet een product code bevatten")
    String serialNumber;
}
