package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomRepairOperationDto {
    Long id;

    @NotBlank(message = "De handeling moet een titel bevatten")
    String title;

    @NotBlank(message = "De handeling moet een beschrijving bevatten")
    String description;

    double price;

    String timestamp;

    Long repair;

    @NotNull
    Long carPart;

}
