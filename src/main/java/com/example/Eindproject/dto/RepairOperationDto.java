package com.example.Eindproject.dto;

import com.example.Eindproject.entity.Repair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotNull
    Long carPart;

    String carPartName;
}