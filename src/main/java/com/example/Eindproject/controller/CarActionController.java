package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CarActionDto;
import com.example.Eindproject.dto.CarDto;
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

    @GetMapping("/add-car-action")
    public String addCarAction(Model model){
        model.addAttribute("carAction", new CarActionDto());

        return "action/add-action";
    }

    @PostMapping("/add-car-action")
    public String addCarAction(@Valid @ModelAttribute("carAction") CarActionDto carActionDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "action/add-action";
        }

        service.createCarAction(carActionDto, false);
        return "redirect:/cars";
    }
}
