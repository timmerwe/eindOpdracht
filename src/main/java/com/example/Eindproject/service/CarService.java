package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;

import java.util.List;

public interface CarService {
    public Long createCar(CarDto carDto);
    public CarDto getCar();
    public List<CarDto> getAllCars();
}
