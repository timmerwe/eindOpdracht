package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InspectionDto {
    Long id;

    Long carId;

    @NotNull
    String plannedDate;

    String status;

    int wantsRepair;

    String licencePlate;

}
