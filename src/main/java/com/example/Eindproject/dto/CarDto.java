package com.example.Eindproject.dto;

import com.example.Eindproject.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    Long id;

    @NotEmpty(message = "Voer een kenteken plaat in.")
    String licencePlate;

    @NotEmpty(message = "Voer een merknaam in.")
    String brand;

    Long customerId;
}
