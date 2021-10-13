package com.example.Eindproject.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
}
