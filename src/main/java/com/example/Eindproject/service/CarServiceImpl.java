package com.example.Eindproject.service;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.CarPdf;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CarMapper;
import com.example.Eindproject.repos.CarPdfRepository;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repos;
    private final CustomerRepository customerRepository;
    private final CarPdfRepository carPdfRepository;

    // initializeren van repositoryies
    public CarServiceImpl(CarRepository repos, CustomerRepository customerRepository, CarPdfRepository carPdfRepository){
        this.repos = repos;
        this.customerRepository = customerRepository;
        this.carPdfRepository = carPdfRepository;
    }

//    Maakt een nieuwe auto aan gekopeld aan een bestaande gebruiker en slaat deze op in de database. Indien er ook een pdf meegegeven is word deze ook toegevoegd.
    @Override
    public Long createCar(CarDto carDto) {
        Car c = CarMapper.fromDtoToEntity(carDto);
        Customer customer = customerRepository.getById(carDto.getCustomerId());
        c.setCustomer(customer);

        if(carDto.getPdfFile() != null && carDto.getPdfFile().length > 0){
            CarPdf pdf = new CarPdf(carDto.getPdfFile());
            carPdfRepository.save(pdf);
            c.setCarPdf(pdf);
        }

        repos.save(c);
        return c.getId();

    }

//    Haalt een specefieke auto op op basis van het meegegeven auto id
    @Transactional
    @Override
    public CarDto getCar(Long id) {
        Car c = repos.getById(id);
        return CarMapper.fromEntityToDto(c);
    }

//    Haalt een lijst op met alle auto's
    @Override
    public ArrayList<CarDto> getAllCars() {
        ArrayList<CarDto> allCustomerDto = new ArrayList<>();
        List<Car> allCars = repos.findAll();
        for (Car car : allCars) {
            CarDto dto = CarMapper.fromEntityToDto(car);
            allCustomerDto.add(dto);
        }
        return allCustomerDto;
    }

//    Haalt een auto pdf op op basis van een auto id
    @Override
    public byte[] getPdf(Long id){
        return carPdfRepository.getById(id).getPdfFile();
    }
}
