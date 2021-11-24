package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;

// Definitie van de bevinding entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De bevinding is gekoppeld aan één inspectie

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Finding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String title;

    @NonNull
    String description;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="inspection_id")
    private Inspection inspection;
}
