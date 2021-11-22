package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;

import java.util.ArrayList;
import java.util.List;

public interface CarService {
    public Long createCar(CarDto carDto);
    public CarDto getCar(Long id);
    public ArrayList<CarDto> getAllCars();
    public byte[] getPdf(Long id);
}
