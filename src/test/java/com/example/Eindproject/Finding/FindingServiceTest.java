package com.example.Eindproject.Finding;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.FindingRepository;
import com.example.Eindproject.service.CarServiceImpl;
import com.example.Eindproject.service.FindingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindingServiceTest {
    @Mock
    private FindingRepository findingRepository;

    @InjectMocks
    private FindingServiceImpl service;


    @Test
    void shouldGetAllFindings(){
        service.getAllFindingsById(2L);
        verify(findingRepository).findAllByInspection_id(2L);
    }
}
