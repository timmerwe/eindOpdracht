package com.example.Eindproject.Inspection;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.InspectionRepository;
import com.example.Eindproject.service.CarServiceImpl;
import com.example.Eindproject.service.InspectionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InspectionServiceTest {
    @Mock
    private InspectionRepository inspectionRepository;

    @InjectMocks
    private InspectionServiceImpl service;


    @Test
    void shouldGetAllInspections() throws ParseException {
        service.getAllInspections();
        verify(inspectionRepository).findAllByWantsRepairAndFinished(2, false);
    }

}
