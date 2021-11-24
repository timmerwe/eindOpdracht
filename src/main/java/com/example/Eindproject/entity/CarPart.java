package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Definitie van de auto onderdelen entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// Een onderdeel kan gekoppeld worden aan 0 of meerdere reparatie activiteiten

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class CarPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String name;

    @NonNull
    double price;

    @NonNull
    String serialNumber;

    @OneToMany(mappedBy = "carPart")
    private List<RepairOperation> repairOperations;
}
