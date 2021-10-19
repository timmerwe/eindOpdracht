package com.example.Eindproject.entity;

import lombok.*;

import javax.persistence.*;

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
