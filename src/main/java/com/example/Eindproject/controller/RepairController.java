package com.example.Eindproject.controller;

import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.CustomerService;
import com.example.Eindproject.service.InspectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RepairController {

    private final CarService service;
    private final CarService carService;
    private final InspectionService inspectionService;

    public RepairController(CarService service, CarService carService, InspectionService inspectionService){
        this.service = service;
        this.carService = carService;
        this.inspectionService = inspectionService;
    }

    @GetMapping("/repair/add-repair/{id}")
    public String addInspection(Model model, @PathVariable("id") Long carId){
        model.addAttribute("repair", new RepairDto());
        model.addAttribute("car",carService.getCar(carId));
        model.addAttribute("inspection", inspectionService.getInspection(carId));

        return "inspection/add-inspection";
    }
}
