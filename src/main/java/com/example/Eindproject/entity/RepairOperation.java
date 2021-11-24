package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

// Definitie van de reparatie activiteit entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De reparatie activiteit is gekoppeld aan één reparatie en bevat 1 reparatie onderdeel en 1 reparatie handeling

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class RepairOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    Date timestamp;

    @NonNull
    double price;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="repair_id")
    private Repair repair;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "carAction_id")
    private CarAction carAction;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "carPart_id")
    private CarPart carPart;
}
