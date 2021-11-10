package com.example.Eindproject.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

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

    @Lob
    byte[] pdfFile;

    @ManyToOne()
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<Inspection> inspections;

    @OneToMany(mappedBy = "car")
    private List<Repair> repairs;
}
