package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepairOperationDto {
    Long id;

    String timestamp;

    double price;

    Long repair;

    @NotNull
    Long carAction;

    String carActionTitle;

    String carActionDescription;

    @NotNull
    Long carPart;

    String carPartName;

    public RepairOperationDto(Long repair, Long carAction, Long carPart) {
        this.repair = repair;
        this.carAction = carAction;
        this.carPart = carPart;
    }
}
