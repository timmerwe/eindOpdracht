package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.mapping.CarMapper;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.InspectionRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InspectionServiceImpl implements InspectionService {

    private final InspectionRepository repos;
    private final CarRepository carRepository;


    public InspectionServiceImpl(InspectionRepository repos, CarRepository carRepository){
        this.repos = repos;
        this.carRepository = carRepository;
    }

    @Override
    public Long createInspection(InspectionDto dto) throws ParseException {
        Car c = carRepository.getById(dto.getCarId());
        //Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getPlannedDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dto.getPlannedDate());
        sdf.applyPattern("yyyy-MM-dd");
        Date newDateString = sdf.parse(sdf.format(date));

        Inspection i = new Inspection(c, newDateString, dto.getStatus());

        repos.save(i);
        return i.getId();

    }

    @Override
    public InspectionDto getInspection(Long id) throws ParseException {
        Inspection i = repos.getById(id);
        return new InspectionDto(i.getId(), i.getCar().getId(), i.getPlannedDate().toString(), i.getStatus(), i.getCar().getLicencePlate());
    }

    @Override
    public ArrayList<InspectionDto> getAllInspections() throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAll();
        for (Inspection i : allInspections) {
            InspectionDto dto = new InspectionDto(i.getId(), i.getCar().getId(), i.getPlannedDate().toString(), i.getStatus(), i.getCar().getLicencePlate());
            allInspectionsDto.add(dto);
        }
        return allInspectionsDto;
    }

    @Override
    public Long changeInspectionStatus(Long id, String status) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setStatus(status);
            repos.save(i);
            return i.getId();
        }
        return null;
    }
}
