package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarPartDto;
import com.example.Eindproject.service.CarPartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarPartController {

    private final CarPartService service;

    public CarPartController(CarPartService service){
        this.service = service;
    }

//    Geeft pagina voor toevoegen van reparatie onderdelen weer
    @GetMapping("/backoffice/add-car-part")
    public String addCarPart(Model model){
        model.addAttribute("carPart", new CarPartDto());

        return "part/add-part";
    }

//    Vangt het formulier af en voeg reparatie onderdeel toe aan de database
    @PostMapping("/backoffice/add-car-part")
    public String addCarPart(@Valid @ModelAttribute("carPart") CarPartDto carPartDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "part/add-part";
        }

        service.createCarPart(carPartDto);
        return "redirect:/backoffice/add-car-part";
    }
}
