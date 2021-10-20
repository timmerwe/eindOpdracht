package com.example.Eindproject.dto;

import com.example.Eindproject.entity.Repair;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class RepairActionDto {
    Long id;

    Date timestamp;

    double price;

    private Repair repair;
}
