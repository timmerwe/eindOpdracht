package com.example.Eindproject.dto;

import javax.validation.constraints.*;

public class CustomerDto {

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CustomerDto(String firstName, String lastName, String email, String postalCode, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
    }

    public CustomerDto() {

    }
}
