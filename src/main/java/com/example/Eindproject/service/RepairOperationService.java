package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.RepairOperationDto;

import java.text.ParseException;
import java.util.ArrayList;

public interface RepairOperationService {
    public Long createRepairOperation(RepairOperationDto repairOperationDto) throws ParseException;
    public RepairOperationDto getRepairOperation(Long id);
    public ArrayList<RepairOperationDto> getAllRepairOperationsById(Long id);
}
