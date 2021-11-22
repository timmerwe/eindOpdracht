package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.CarPartDto;

import java.util.ArrayList;

public interface CarPartService {
    public Long createCarPart(CarPartDto carPartDto);
    public ArrayList<CarPartDto> getAllCarParts();
}
