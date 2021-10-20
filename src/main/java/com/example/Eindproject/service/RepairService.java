package com.example.Eindproject.service;

import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.dto.RepairDto;

import java.text.ParseException;
import java.util.ArrayList;

public interface RepairService {
    public Long createRepair(RepairDto repairDto) throws ParseException;
    public RepairDto getRepair(Long id) throws ParseException;
    public ArrayList<RepairDto> getAllRepair() throws ParseException;
    public ArrayList<RepairDto> getAllRepairByCarId(Long id) throws ParseException;
    public void changeRepairStatus(Long id, String status);
}
