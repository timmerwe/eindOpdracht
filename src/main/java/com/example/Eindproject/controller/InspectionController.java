package com.example.Eindproject.controller;

import com.example.Eindproject.dto.FindingDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.service.CarService;
import com.example.Eindproject.service.FindingService;
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
public class InspectionController {

    private final InspectionService service;
    private final CarService carService;
    private final FindingService findingService;

    public InspectionController(InspectionService service, CarService carService, FindingService findingService){
        this.service = service;
        this.carService = carService;
        this.findingService = findingService;
    }

//    Geeft pagina voor toevoegen van inspectie aan een auto weer
    @GetMapping("/administration/car/add-inspection/{id}")
    public String addInspection(Model model, @PathVariable("id") Long carId){
        model.addAttribute("inspection", new InspectionDto());
        model.addAttribute("car",carService.getCar(carId));

        return "inspection/add-inspection";
    }

//    Vangt het formulier van toevoegen inspectie af en voegt deze toe aan de database
    @PostMapping("/administration/car/add-inspection/{id}")
    public String addInspection(@Valid @ModelAttribute("inspection") InspectionDto inspectionDto, BindingResult bindingResult, Model model, @PathVariable("id") Long carId) throws ParseException {
        model.addAttribute("car",carService.getCar(carId));
        if(bindingResult.hasErrors()) {
            return "inspection/add-inspection";
        }
        inspectionDto.setCarId(carId);
        inspectionDto.setStatus("Ingepland");
        service.createInspection(inspectionDto);

        return "redirect:/inspections";
    }

//    Geeft pagina weer waarop alle inspecties staan met status ingepland of in uitvoering
    @GetMapping("/inspections")
    public String showAllInspections(Model model) throws ParseException {
        model.addAttribute("inspections", service.getAllInspections());

        return "inspection/inspections";
    }

//    Geeft de pagina weer waarop je een inspectie kan bewerken
    @GetMapping("/mechanic/inspection/edit-inspection/{id}")
    public String editInspection(Model model, @PathVariable("id") Long inspectionId) throws ParseException {
        model.addAttribute("inspection", service.getInspection(inspectionId));
        model.addAttribute("finding", new FindingDto());
        model.addAttribute("allFindings", findingService.getAllFindingsById(inspectionId));

        return "inspection/edit-inspection";
    }

//    Vangt het voeg bevindingen toe formulier af en verwerkt deze naar de database
    @PostMapping("/mechanic/inspection/edit-inspection/{id}")
    public String editInspection(@Valid @ModelAttribute("finding") FindingDto findingDto, BindingResult bindingResult, Model model, @PathVariable("id") Long inspectionId) throws ParseException {
        model.addAttribute("inspection",service.getInspection(inspectionId));
        model.addAttribute("allFindings", findingService.getAllFindingsById(inspectionId));
        if(bindingResult.hasErrors()) {
            return "inspection/edit-inspection";
        }
        findingDto.setInspection(inspectionId);
        findingService.createFinding(findingDto);
        return "redirect:/mechanic/inspection/edit-inspection/" + inspectionId;
    }

//    Vangt het verrander status formulier af en wijzigd deze in de database
    @PostMapping("/mechanic/inspection/edit-inspection-status/{id}")
    public String editInspectionStatus(@Valid @ModelAttribute("inspection") InspectionDto inspectionDto, BindingResult bindingResult, Model model, @PathVariable("id") Long inspectionId) throws ParseException {
        service.changeInspectionStatus(inspectionId, inspectionDto.getStatus());
        return "redirect:/mechanic/inspection/edit-inspection/" + inspectionId;
    }

//    Vangt het wil wil reparatie formulier af en wijzigd deze in de database
    @PostMapping("/mechanic/inspection/edit-inspection-repair/{id}")
    public String editInspectionWantsRepair(@Valid @ModelAttribute("inspection") InspectionDto inspectionDto, BindingResult bindingResult, Model model, @PathVariable("id") Long inspectionId) throws ParseException {
        service.changeInspectionWantsRepair(inspectionId, inspectionDto.getWantsRepair());
        return "redirect:/mechanic/inspection/edit-inspection/" + inspectionId;
    }

//    Zet de inspectie op afgerond zodat deze uit de voorkant van het systeem verdwijnd
    @GetMapping("/inspection/finished/{id}")
    public String editInspectionFinished(@PathVariable("id") Long inspectionId) {
        service.setToFinished(inspectionId);
        return "redirect:/administration/call-list/";
    }
}
