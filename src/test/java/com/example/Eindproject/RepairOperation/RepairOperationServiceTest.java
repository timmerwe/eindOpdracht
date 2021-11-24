package com.example.Eindproject.RepairOperation;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.RepairOperationRepository;
import com.example.Eindproject.service.CarServiceImpl;
import com.example.Eindproject.service.RepairOperationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepairOperationServiceTest {
    @Mock
    private RepairOperationRepository repairOperationRepository;

    @InjectMocks
    private RepairOperationServiceImpl service;


    @Test
    void shouldGetAllRepairOperations(){
        service.getAllRepairOperationsById(2L);
        verify(repairOperationRepository).findAllByRepair_Id(2L);
    }

}
