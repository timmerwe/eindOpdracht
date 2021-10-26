package com.example.Eindproject.service;

import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.dto.RepairOperationDto;
import com.example.Eindproject.entity.*;
import com.example.Eindproject.mapping.InspectionMapper;
import com.example.Eindproject.repos.CarActionRepository;
import com.example.Eindproject.repos.CarPartRepository;
import com.example.Eindproject.repos.RepairOperationRepository;
import com.example.Eindproject.repos.RepairRepository;
import org.aspectj.asm.internal.CharOperation;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepairOperationServiceImpl implements RepairOperationService{

    private final RepairOperationRepository repos;
    private final CarActionRepository carActionRepository;
    private final CarPartRepository carPartRepository;
    private final RepairRepository repairRepository;

    public RepairOperationServiceImpl(RepairOperationRepository repairOperationRepository, CarActionRepository carActionRepository, CarPartRepository carPartRepository, RepairRepository repairRepository){
        this.repos = repairOperationRepository;
        this.carActionRepository = carActionRepository;
        this.carPartRepository = carPartRepository;
        this.repairRepository = repairRepository;
    }
    @Override
    public Long createRepairOperation(RepairOperationDto dto) throws ParseException {
        CarAction a = carActionRepository.getById(dto.getCarAction());
        CarPart p = carPartRepository.getById(dto.getCarPart());
        Repair r = repairRepository.getById(dto.getId());

        Date date = new java.util.Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date stringDate = sdf.parse(new Timestamp(date.getTime()).toString());
        sdf.applyPattern("yyyy-MM-dd");
        Date newDateString = sdf.parse(sdf.format(stringDate));

        double price = a.getPrice() + p.getPrice();

        RepairOperation o = new RepairOperation(newDateString, price, r, a, p);

        repos.save(o);
        return o.getId();
    }

    @Override
    public RepairOperationDto getRepairOperation(Long id) {
        return null;
    }

    @Override
    public ArrayList<RepairOperationDto> getAllRepairOperationsById(Long id) {
        ArrayList<RepairOperationDto> allRepairOperationsDto = new ArrayList<>();
        List<RepairOperation> allRepairOperations = repos.findAllByRepair_Id(id);
        for (RepairOperation r : allRepairOperations) {
            RepairOperationDto dto = new RepairOperationDto(r.getId(), r.getTimestamp().toString(), r.getPrice(), r.getRepair().getId(), r.getCarAction().getId(), r.getCarAction().getTitle(), r.getCarPart().getId(), r.getCarPart().getName());
            allRepairOperationsDto.add(dto);
        }
        return allRepairOperationsDto;
    }
}
