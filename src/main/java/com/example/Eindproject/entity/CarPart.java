package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
