package com.example.Eindproject.service;

import com.example.Eindproject.dto.RepairOperationDto;

import java.text.ParseException;
import java.util.ArrayList;

public interface RepairOperationService {
    public void createRepairOperation(RepairOperationDto repairOperationDto) throws ParseException;
    public ArrayList<RepairOperationDto> getAllRepairOperationsById(Long id);
}
