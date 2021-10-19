package com.example.Eindproject.mapping;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;


public class CarMapper {
    public static Car fromDtoToEntity (CarDto dto) {
        return new Car(dto.getLicencePlate(), dto.getBrand());
    }

    public static CarDto fromEntityToDto (Car c) {
        return new CarDto(c.getId(), c.getLicencePlate(), c.getBrand(), c.getCustomer().getId(), c.getCustomer().getFirstName());
    }

}
