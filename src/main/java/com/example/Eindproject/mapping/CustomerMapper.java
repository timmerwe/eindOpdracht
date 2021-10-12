package com.example.Eindproject.mapping;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.entity.Customer;

public class CustomerMapper {

    public static Customer fromDtoToEntity (CustomerDto dto) {
        return new Customer(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPostalCode(), dto.getAddress(), dto.getCity());
    }

    public static CustomerDto fromEntityToDto (Customer c) {
        return new CustomerDto(c.getFirstName(), c.getLastName(), c.getEmail(), c.getPostalCode(), c.getAddress(), c.getCity());
    }
}
