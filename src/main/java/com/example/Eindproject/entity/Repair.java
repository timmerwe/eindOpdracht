package com.example.Eindproject.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Definitie van de reparatie entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De reparatie is gekoppeld aan één auto, kan 0 of meerdere reparatie activiteiten bevatten en kan 0 of 1 inspectie bevatten

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String status;

    @NonNull
    String arrangements;

    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date plannedDate;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;

    @OneToMany(mappedBy = "repair")
    private List<RepairOperation> repairOperations;

    boolean pickupApointment = false;
}
