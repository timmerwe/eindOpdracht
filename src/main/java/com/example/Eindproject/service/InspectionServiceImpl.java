package com.example.Eindproject.service;

import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.mapping.InspectionMapper;
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

        Inspection i = InspectionMapper.fromDtoToEntity(dto, c);

        repos.save(i);
        return i.getId();

    }

    @Override
    public InspectionDto getInspection(Long id) throws ParseException {
        Inspection i = repos.getById(id);
        return InspectionMapper.fromEntityToDto(i);
    }

    @Override
    public ArrayList<InspectionDto> getAllInspections() throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAll();
        for (Inspection i : allInspections) {
            InspectionDto dto = InspectionMapper.fromEntityToDto(i);
            allInspectionsDto.add(dto);
        }
        return allInspectionsDto;
    }

    @Override
    public ArrayList<InspectionDto> getAllInspectionsByCarId(Long id) throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAllByCar_id(id);
        for (Inspection i : allInspections) {
            InspectionDto dto = InspectionMapper.fromEntityToDto(i);
            allInspectionsDto.add(dto);
        }
        return allInspectionsDto;
    }

    @Override
    public void changeInspectionStatus(Long id, String status) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setStatus(status);
            repos.save(i);
        }
    }

    @Override
    public void changeInspectionWantsRepair(Long id, int wantsRepair) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setWantsRepair(wantsRepair);
            repos.save(i);
        }
    }
}
