package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.repos.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repos;

    public CarServiceImpl(CarRepository repos){
        this.repos = repos;
    }

    @Override
    public Long createCar(CarDto carDto) {
        Car c = new Car(carDto.getLicencePlate(), carDto.getBrand());
        repos.save(c);
        return c.getId();

    }

    @Override
    public CarDto getCar() {
        return null;
    }

    @Override
    public List<CarDto> getAllCars() {
        return null;
    }
}
