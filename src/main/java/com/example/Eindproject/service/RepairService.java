package com.example.Eindproject.service;

import com.example.Eindproject.dto.RepairDto;

import java.text.ParseException;
import java.util.ArrayList;

public interface RepairService {
    public void createRepair(RepairDto repairDto) throws ParseException;
    public RepairDto getRepair(Long id) throws ParseException;
    public ArrayList<RepairDto> getAllRepair() throws ParseException;
    public void changeRepairStatus(Long id, String status);
    public Object[][] getAllRepairsByStatus(String status, boolean pickupApointment) throws ParseException;
    public void setToFinished(Long id);
}
