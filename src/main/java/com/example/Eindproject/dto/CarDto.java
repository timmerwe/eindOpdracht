package com.example.Eindproject.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CarDto {
    @NonNull
    Long id;

    @NonNull
    @NotEmpty(message = "Voer een kenteken plaat in.")
    String licencePlate;

    @NonNull
    @NotEmpty(message = "Voer een merknaam in.")
    String brand;

    @NonNull
    Long customerId;

    @NonNull
    String customerName;

    Long pdfFileId;

    byte[] pdfFile;
}
