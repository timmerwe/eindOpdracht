package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.CarDto;

import java.util.ArrayList;

public interface CarActionService {
    public Long createCarAction(CarActionDto carActionDto, boolean customAction);
    public CarActionDto getCarAction(Long id);
    public ArrayList<CarActionDto> getAllCarActions();
}
