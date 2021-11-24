package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.service.CarActionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CarActionController {

    private final CarActionService service;

    public CarActionController(CarActionService service){
        this.service = service;
    }

//    Geeft pagina voor toevoegen reparatie handeling weer
    @GetMapping("/backoffice/add-car-action")
    public String addCarAction(Model model){
        model.addAttribute("carAction", new CarActionDto());

        return "action/add-action";
    }

//    Vangt het formulier af en voegt de reparatie handeling toe aan de database
    @PostMapping("/backoffice/add-car-action")
    public String addCarAction(@Valid @ModelAttribute("carAction") CarActionDto carActionDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "action/add-action";
        }

        service.createCarAction(carActionDto, false);
        return "redirect:/backoffice/add-car-action/";
    }
}
