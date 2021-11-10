package com.example.Eindproject.mapping;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Inspection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InspectionMapper {
    public static Inspection fromDtoToEntity (InspectionDto dto, Car c) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dto.getPlannedDate());
        sdf.applyPattern("yyyy-MM-dd");
        Date newDateString = sdf.parse(sdf.format(date));

        return new Inspection(c, newDateString, dto.getStatus());
    }

    public static InspectionDto fromEntityToDto (Inspection i) throws ParseException {
        return new InspectionDto(i.getId(), i.getCar().getId(), i.getPlannedDate().toString(), i.getStatus(), i.getWantsRepair(), i.getCar().getLicencePlate());
    }
}
