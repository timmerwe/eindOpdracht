package com.example.Eindproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// Definitie van de auto pdf entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De auto pdf is gekoppeld aan één klant

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarPdf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Lob
    byte[] pdfFile;

    @OneToOne(mappedBy = "carPdf")
    private Car car;

    public CarPdf(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }
}
