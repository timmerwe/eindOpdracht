package com.example.Eindproject.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String status;

    String arrangements;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date plannedDate;
}
