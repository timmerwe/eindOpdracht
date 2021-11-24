package com.example.Eindproject.CarAction;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.CarAction;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CarActionRepository;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.service.CarActionServiceImpl;
import com.example.Eindproject.service.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarActionServiceTest {
    @Mock
    private CarActionRepository carActionRepository;

    @InjectMocks
    private CarActionServiceImpl service;


    @Test
    void shouldGetAllCarActions(){
        service.getAllCarActions();
        verify(carActionRepository).findAllByCustomAction(false);
    }

//    @Test
//    void shouldAddCarAction(){
//        CarAction a = createCarAction();
//        CarActionDto dto = new CarActionDto(a.getTitle(), a.getDescription(), a.getPrice());
//        dto.setId(4L);
//
//        when(carActionRepository.save(any(CarAction.class))).thenReturn(a);
//        long i = service.createCarAction(dto, a.isCustomAction());
//
//        assertEquals(a.getId(), i);
//    }


    private CarAction createCarAction(){
        CarAction c = new CarAction();
        c.setId(4L);
        c.setTitle("Vervangen lamp");
        c.setDescription("We vervangen de lamp van de auto");
        c.setPrice(20);
        c.setCustomAction(false);
        return c;
    }

}
