package com.example.Eindproject.service;

import com.example.Eindproject.dto.InspectionDto;

import java.text.ParseException;
import java.util.ArrayList;

public interface InspectionService {
    public Long createInspection(InspectionDto inspectionDto) throws ParseException;
    public InspectionDto getInspection(Long id) throws ParseException;
    public ArrayList<InspectionDto> getAllInspections() throws ParseException;
    public Long changeInspectionStatus(Long id, String status);
}
