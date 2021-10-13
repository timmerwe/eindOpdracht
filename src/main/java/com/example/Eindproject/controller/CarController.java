package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarController {
    private final CarService service;
    private final CustomerService customerService;

    public CarController(CarService service, CustomerService customerService){

        this.service = service;
        this.customerService = customerService;
    }

    @GetMapping("/add-car")
    public String addCar(Model model){
        model.addAttribute("car", new CarDto());
        model.addAttribute("customers", customerService.getAll());

        return "car/add-car";
    }

    @PostMapping("/add-car")
    public String addCar(@Valid @ModelAttribute("car") CarDto carDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "car/add-car";
        }

       service.createCar(carDto);
        return "car/add-car";
    }
}
