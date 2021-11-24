package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

// Definitie van de auto handelingen entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// Een auto handeling kan gekoppeld worden aan 0 of meerdere reparatie activiteiten

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class CarAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String title;

    @NonNull
    String description;

    @NonNull
    double price;

    @NonNull
    boolean customAction;

    @OneToMany(mappedBy = "carAction")
    private List<RepairOperation> repairOperations;
}
