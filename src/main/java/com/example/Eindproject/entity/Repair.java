package com.example.Eindproject.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "inspection_id")
    private Inspection inspection;

    @OneToMany(mappedBy = "repair")
    private List<RepairAction> repairActions;
}
