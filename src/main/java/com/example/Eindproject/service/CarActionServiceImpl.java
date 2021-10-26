package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.CarAction;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CarMapper;
import com.example.Eindproject.repos.CarActionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarActionServiceImpl implements CarActionService{

    private final CarActionRepository repos;

    public CarActionServiceImpl(CarActionRepository repos){
        this.repos = repos;
    }

    @Override
    public Long createCarAction(CarActionDto carActionDto, boolean customAction) {
        CarAction c = new CarAction(carActionDto.getTitle(), carActionDto.getDescription(), carActionDto.getPrice(), customAction);
        repos.save(c);
        return c.getId();
    }

    @Override
    public CarActionDto getCarAction(Long id) {
        CarAction a = repos.getById(id);
        return new CarActionDto(a.getId(), a.getTitle(), a.getDescription(), a.getPrice());
    }

    @Override
    public ArrayList<CarActionDto> getAllCarActions() {
        ArrayList<CarActionDto> allCarActionsDto = new ArrayList<>();
        List<CarAction> allCarActions = repos.findAllByCustomAction(false);
        for (CarAction a : allCarActions) {
            CarActionDto dto = new CarActionDto(a.getId(), a.getTitle(), a.getDescription(), a.getPrice());
            allCarActionsDto.add(dto);
        }
        return allCarActionsDto;
    }
}
