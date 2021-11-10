package com.example.Eindproject.controller;

import com.example.Eindproject.dto.*;
import com.example.Eindproject.service.*;
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
    private final CarPartService carPartService;
    private final CarActionService carActionService;
    private final RepairOperationService repairOperationService;

    public RepairController(RepairService service, CarService carService, InspectionService inspectionService, CarPartService carPartService, CarActionService carActionService, RepairOperationService repairOperationService){
        this.service = service;
        this.carService = carService;
        this.inspectionService = inspectionService;
        this.carPartService = carPartService;
        this.carActionService = carActionService;
        this.repairOperationService = repairOperationService;
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
        model.addAttribute("repairOperation", new RepairOperationDto());
        model.addAttribute("allActions", carActionService.getAllCarActions());
        model.addAttribute("allParts", carPartService.getAllCarParts());
        model.addAttribute("allRepairOperations", repairOperationService.getAllRepairOperationsById(repairID));
        model.addAttribute("customRepairOperation", new CustomRepairOperationDto());

        return "repair/edit-repair";
    }

    @PostMapping("/repair/add-repair-operation/{id}")
    public String addRepairOperation(@Valid @ModelAttribute("repairOperation") RepairOperationDto repairOperationDto, BindingResult bindingResult, Model model, @PathVariable("id") Long repairId) throws ParseException {
        model.addAttribute("repair", service.getRepair(repairId));
        model.addAttribute("allActions", carActionService.getAllCarActions());
        model.addAttribute("allParts", carPartService.getAllCarParts());
        if(bindingResult.hasErrors()) {
            return "repair/edit-repair";
        }
        repairOperationDto.setRepair(repairId);

        repairOperationService.createRepairOperation(repairOperationDto);

        return "redirect:/repair/edit-repair/" + repairId;
    }

    @PostMapping("/repair/add-custom-repair-operation/{id}")
    public String addCustomRepairOperation(@Valid @ModelAttribute("customRepairOperation") CustomRepairOperationDto cro, BindingResult bindingResult, Model model, @PathVariable("id") Long repairId) throws ParseException {
        model.addAttribute("repair", service.getRepair(repairId));
        model.addAttribute("allActions", carActionService.getAllCarActions());
        model.addAttribute("allParts", carPartService.getAllCarParts());
        if(bindingResult.hasErrors()) {
            return "repair/edit-repair";
        }
        CarActionDto newAction = new CarActionDto(cro.getTitle(), cro.getDescription(), cro.getPrice());
        Long newActionId = carActionService.createCarAction(newAction, true);
        RepairOperationDto repairOperationDto = new RepairOperationDto(repairId, newActionId, cro.getCarPart());
        repairOperationService.createRepairOperation(repairOperationDto);

        return "redirect:/repair/edit-repair/" + repairId;
    }

    @PostMapping("/repair/edit-repair-status/{id}")
    public String editRepairStatus(@ModelAttribute("repair") RepairDto repairDto, @PathVariable("id") Long repairId) {
        service.changeRepairStatus(repairId, repairDto.getStatus());
        return "redirect:/repair/edit-repair/" + repairId;
    }

}
