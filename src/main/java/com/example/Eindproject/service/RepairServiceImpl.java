package com.example.Eindproject.service;

import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.entity.Repair;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.InspectionRepository;
import com.example.Eindproject.repos.RepairRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService{

    private final RepairRepository repos;
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;


    public RepairServiceImpl(RepairRepository repos, CarRepository carRepository, InspectionRepository inspectionRepository){
        this.repos = repos;
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
    }

    @Override
    public Long createRepair(RepairDto repairDto) throws ParseException {
        Car c = carRepository.getById(repairDto.getCarId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(repairDto.getPlannedDate());
        sdf.applyPattern("yyyy-MM-dd");
        Date newDateString = sdf.parse(sdf.format(date));

        Repair r = new Repair(repairDto.getStatus(), repairDto.getArrangements(), newDateString, c);

        if(repairDto.getInspectionId() != null){
            Inspection i = inspectionRepository.getById(repairDto.getInspectionId());
            r.setInspection(i);
        }

        repos.save(r);
        return r.getId();

    }

    @Override
    public RepairDto getRepair(Long id) throws ParseException {
        Repair r = repos.getById(id);
        Long inspection = null;
        if(r.getInspection() != null){
            inspection = r.getInspection().getId();
        }
        return new RepairDto(r.getId(), r.getStatus(), r.getArrangements(), r.getPlannedDate().toString(), inspection, r.getCar().getId(), r.getCar().getLicencePlate());
    }

    @Override
    public ArrayList<RepairDto> getAllRepair() throws ParseException {
        ArrayList<RepairDto> allRepairsDto = new ArrayList<>();
        List<Repair> allRepairs = repos.findAll();
        for (Repair r : allRepairs) {
            Long inspection = null;
            if(r.getInspection() != null){
                inspection = r.getInspection().getId();
            }
            RepairDto dto = new RepairDto(r.getId(), r.getStatus(), r.getArrangements(), r.getPlannedDate().toString(), inspection, r.getCar().getId(), r.getCar().getLicencePlate());
            allRepairsDto.add(dto);
        }
        return allRepairsDto;
    }

    @Override
    public ArrayList<RepairDto> getAllRepairByCarId(Long id) throws ParseException {
        return null;
    }

    @Override
    public void changeRepairStatus(Long id, String status) {
        Optional<Repair> rOptional = repos.findById(id);
        if (rOptional.isPresent()){
            Repair r = rOptional.get();
            r.setStatus(status);
            repos.save(r);
        }
    }
}
