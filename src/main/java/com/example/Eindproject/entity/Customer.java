package com.example.Eindproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String postalCode;

    private String address;

    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Car> cars;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String postalCode, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
    }

    public Customer() {

    }

}
