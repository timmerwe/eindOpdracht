package com.example.Eindproject.dto;

import com.example.Eindproject.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
