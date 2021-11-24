package com.example.Eindproject.Repair;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.RepairRepository;
import com.example.Eindproject.service.CarServiceImpl;
import com.example.Eindproject.service.RepairServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepairServiceTest {
    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private RepairServiceImpl service;


    @Test
    void shouldGetAllRepairs() throws ParseException {
        service.getAllRepair();
        verify(repairRepository).findAllByStatusAndPickupApointment("Ingepland", false);
    }


}
