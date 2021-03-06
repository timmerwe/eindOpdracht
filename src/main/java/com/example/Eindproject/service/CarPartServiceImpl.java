package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarPartDto;
import com.example.Eindproject.entity.CarPart;
import com.example.Eindproject.repos.CarPartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarPartServiceImpl implements CarPartService{

    private final CarPartRepository repos;

// initializeren van repositoryies
    public CarPartServiceImpl(CarPartRepository repos){
        this.repos = repos;
    }

//    Voegt een nieuw auto onderdeel toe aan de database
    @Override
    public Long createCarPart(CarPartDto carPartDto) {
        CarPart c = new CarPart(carPartDto.getName(), carPartDto.getPrice(), carPartDto.getSerialNumber());
        repos.save(c);
        return c.getId();
    }

//    Haalt alle auto onderdelen op uit de database
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
