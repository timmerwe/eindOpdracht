package com.example.Eindproject.controller;

import com.example.Eindproject.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class personController {
    @GetMapping("/add-person")
    public String function(Model model){
        PersonDto pd = new PersonDto();
        model.addAttribute("FormData", pd);

        return "add-customer";
    }


    @PostMapping("/add-person")
    public String function(@Valid @ModelAttribute("formData") PersonDto personDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "add-customer";
        }

        //service.createCustomer(personDto);

        return "";
    }
}
