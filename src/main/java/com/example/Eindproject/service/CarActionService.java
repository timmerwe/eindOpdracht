package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarActionDto;

import java.util.ArrayList;

public interface CarActionService {
    public Long createCarAction(CarActionDto carActionDto, boolean customAction);
    public ArrayList<CarActionDto> getAllCarActions();
}
