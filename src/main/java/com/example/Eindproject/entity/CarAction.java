package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
