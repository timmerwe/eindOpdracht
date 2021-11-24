package com.example.Eindproject.CarPart;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarPartRepository;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.service.CarPartServiceImpl;
import com.example.Eindproject.service.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarPartServiceTest {
    @Mock
    private CarPartRepository carPartRepository;

    @InjectMocks
    private CarPartServiceImpl service;


    @Test
    void shouldGetAllCarParts(){
        service.getAllCarParts();
        verify(carPartRepository).findAll();
    }

}
