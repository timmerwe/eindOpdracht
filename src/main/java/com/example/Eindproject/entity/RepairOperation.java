package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
