package com.example.Eindproject.controller;

import com.example.Eindproject.dto.FindingDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.CustomerService;
import com.example.Eindproject.service.InspectionService;
import com.example.Eindproject.service.RepairService;
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
public class RepairController {

    private final RepairService service;
    private final CarService carService;
    private final InspectionService inspectionService;

    public RepairController(RepairService service, CarService carService, InspectionService inspectionService){
        this.service = service;
        this.carService = carService;
        this.inspectionService = inspectionService;
    }

    @GetMapping("/repair/add-repair/{id}")
    public String addInspection(Model model, @PathVariable("id") Long carId) throws ParseException {
        model.addAttribute("repair", new RepairDto());
        model.addAttribute("car",carService.getCar(carId));
        model.addAttribute("inspection", inspectionService.getAllInspectionsByCarId(carId));

        return "repair/add-repair";
    }

    @PostMapping("/repair/add-repair/{id}")
    public String addRepair(@Valid @ModelAttribute("repair") RepairDto repairDto, BindingResult bindingResult, Model model, @PathVariable("id") Long carId) throws ParseException {
        model.addAttribute("car",carService.getCar(carId));
        model.addAttribute("inspection", inspectionService.getAllInspectionsByCarId(carId));
        if(bindingResult.hasErrors()) {
            return "repair/add-repair";
        }
        repairDto.setCarId(carId);
        repairDto.setStatus("Ingepland");
        service.createRepair(repairDto);

        return "redirect:/cars";
    }

    @GetMapping("/repairs")
    public String showAllRepairs(Model model) throws ParseException {
        model.addAttribute("repairs", service.getAllRepair());

        return "repair/repairs";
    }

    @GetMapping("/repair/edit-repair/{id}")
    public String editRepair(Model model, @PathVariable("id") Long repairID) throws ParseException {
        model.addAttribute("repair", service.getRepair(repairID));

        return "repair/edit-repair";
    }
}
