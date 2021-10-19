package com.example.Eindproject.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class RepairDto {
    Long id;

    String status;

    String arrangements;

    String plannedDate;
}
