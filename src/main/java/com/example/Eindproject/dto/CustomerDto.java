package com.example.Eindproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    Long id;

    @NotBlank(message = "Voornaam mag niet leeg zijn.")
    private String firstName;

    @NotBlank(message = "Achternaam mag niet leeg zijn.")
    private String lastName;

    @NotBlank(message = "Email mag niet leeg zijn.")
    @Email(message = "Email ongeldig")
    private String email;

    @NotBlank(message = "Postcode mag niet leeg zijn.")
    @Size(min = 6, max = 6, message = "Postcode moet 6 karaters lang zijn.")
    private String postalCode;

    @NotBlank(message = "Adres mag niet leeg zijn")
    private String address;

    @NotBlank(message = "Plaats naam mag niet leeg zijn")
    private String city;

}
