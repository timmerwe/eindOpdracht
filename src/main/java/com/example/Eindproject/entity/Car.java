package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Definitie van de auto entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De auto is gekoppeld aan één klant en kan 0 of meerdere reparaties en inspecties bevatten

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    String licencePlate;

    @NonNull
    String brand;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<Inspection> inspections;

    @OneToMany(mappedBy = "car")
    private List<Repair> repairs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carPdf_id")
    private CarPdf carPdf;
}
