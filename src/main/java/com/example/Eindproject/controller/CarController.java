package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.CustomerService;
import com.example.Eindproject.service.InspectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class CarController {
    private final CarService service;
    private final CustomerService customerService;
    private final InspectionService inspectionService;

    public CarController(CarService service, CustomerService customerService, InspectionService inspectionService){

        this.service = service;
        this.customerService = customerService;
        this.inspectionService = inspectionService;
    }

    @GetMapping("/add-car")
    public String addCar(Model model){
        model.addAttribute("car", new CarDto());
        model.addAttribute("customers", customerService.getAll());

        return "car/add-car";
    }

    @PostMapping("/add-car")
    public String addCar(@Valid @ModelAttribute("car") CarDto carDto, BindingResult bindingResult, Model model){
        model.addAttribute("customers", customerService.getAll());
        if(bindingResult.hasErrors()) {
            return "car/add-car";
        }

       service.createCar(carDto);
        return "redirect:/cars";
    }

    @GetMapping("/cars")
    public String showAllCustomers(Model model){
        model.addAttribute("cars", service.getAllCars());

        return "car/cars";
    }


}
