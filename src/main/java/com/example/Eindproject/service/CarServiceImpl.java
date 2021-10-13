package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repos;
    private final CustomerRepository customerRepository;

    public CarServiceImpl(CarRepository repos, CustomerRepository customerRepository){

        this.repos = repos;
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createCar(CarDto carDto) {
        Car c = new Car(carDto.getLicencePlate(), carDto.getBrand());
        if(carDto.getCustomerId() != null){
            Customer customer = customerRepository.getById(carDto.getCustomerId());
            c.setCustomer(customer);
        }
        repos.save(c);
        return c.getId();

    }

    @Override
    public CarDto getCar() {
        return null;
    }

    @Override
    public List<CarDto> getAllCars() {
        return null;
    }
}
