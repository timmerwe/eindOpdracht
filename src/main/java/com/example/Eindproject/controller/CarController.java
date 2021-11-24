package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.CustomerService;
import com.example.Eindproject.service.InspectionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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

//    Geeft de pagina voor het toevoegen van een auto weer
    @GetMapping("/administration/add-car")
    public String addCar(Model model){
        model.addAttribute("car", new CarDto());
        model.addAttribute("customers", customerService.getAll());

        return "car/add-car";
    }

//    Vangt het formulier van een auto toevoegen af en zet deze auto in de database
    @PostMapping("/administration/add-car")
    public String addCar(@Valid @ModelAttribute("car") CarDto carDto, @RequestParam("carPdf") MultipartFile file, BindingResult bindingResult, Model model) throws IOException {
        model.addAttribute("customers", customerService.getAll());
        if(bindingResult.hasErrors()) {
            return "car/add-car";
        }

        carDto.setPdfFile(file.getBytes());
       service.createCar(carDto);
        return "redirect:/cars";
    }

//    Haalt het pdf document van de auto op en geeft deze weer
    @Transactional
    @GetMapping(value = "/download/pdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] showCarDocuments(@PathVariable Long id){
        return service.getPdf(id);
    }

//    Geeft de pagina weer waarop alle toegevoegde auto's staan
    @GetMapping("/cars")
    public String showAllCustomers(Model model){
        model.addAttribute("cars", service.getAllCars());

        return "car/cars";
    }


}
