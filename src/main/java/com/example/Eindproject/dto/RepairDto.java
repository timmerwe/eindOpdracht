package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {
    Long id;

    String status;

    @NotEmpty
    String arrangements;

    @NotEmpty
    String plannedDate;

    Long inspectionId;

    Long carId;

    String licencePlate;
}
