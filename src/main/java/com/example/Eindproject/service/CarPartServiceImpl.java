package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.CarPartDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.CarAction;
import com.example.Eindproject.entity.CarPart;
import com.example.Eindproject.mapping.CarMapper;
import com.example.Eindproject.repos.CarPartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarPartServiceImpl implements CarPartService{

    private final CarPartRepository repos;

    public CarPartServiceImpl(CarPartRepository repos){
        this.repos = repos;
    }

    @Override
    public Long createCarPart(CarPartDto carPartDto) {
        CarPart c = new CarPart(carPartDto.getName(), carPartDto.getPrice(), carPartDto.getSerialNumber());
        repos.save(c);
        return c.getId();
    }

    @Override
    public CarPartDto getCarPart(Long id) {
        CarPart p = repos.getById(id);
        return new CarPartDto(p.getId(), p.getName(), p.getPrice(), p.getSerialNumber());
    }

    @Override
    public ArrayList<CarPartDto> getAllCarParts() {
        ArrayList<CarPartDto> allCarPartsDto = new ArrayList<>();
        List<CarPart> allCarParts = repos.findAll();
        for (CarPart p : allCarParts) {
            CarPartDto dto = new CarPartDto(p.getId(), p.getName(), p.getPrice(), p.getSerialNumber());
            allCarPartsDto.add(dto);
        }
        return allCarPartsDto;
    }
}
