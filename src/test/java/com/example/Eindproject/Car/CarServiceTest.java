package com.example.Eindproject.Car;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CarMapper;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import com.example.Eindproject.service.CarServiceImpl;
import com.example.Eindproject.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl service;


    @Test
    void shouldGetAllCars(){
        service.getAllCars();
        verify(carRepository).findAll();
    }

    @Test
    void shouldGetCar(){
        when(carRepository.getById(4L)).thenReturn(createCar());
        service.getCar(4L);
        verify(carRepository).getById(4L);
    }

//    @Test
//    void shouldAddCar(){
//        Car c = createCar();
//
//        when(carRepository.save(any(Car.class))).thenReturn(c);
//        when(custRepos.getById(4L)).thenReturn(createCustomer());
//         long i = service.createCar(createCarDto());
//
//        assertEquals(c.getId(), i);
//    }


    private CarDto createCarDto(){
        CarDto cDto = new CarDto();
        cDto.setId(4L);
        cDto.setLicencePlate("AB-123-C");
        cDto.setBrand("Audi");
        cDto.setCustomerId(4L);
        return cDto;
    }

    private Car createCar(){
        Car c = new Car();
        c.setId(4L);
        c.setLicencePlate("AB-123-C");
        c.setBrand("Audi");
        c.setCustomer(createCustomer());
        return c;
    }

    private Customer createCustomer(){
        Customer c = new Customer();
        c.setId(0);
        c.setFirstName("Tom");
        c.setLastName("Tiedeman");
        c.setEmail("Tom@tiedeman.nl");
        c.setPhoneNumber("06382729");
        c.setPostalCode("1234AB");
        c.setAddress("Dropstraat 1");
        c.setCity("Apeldoorn");
        return c;
    }
}
