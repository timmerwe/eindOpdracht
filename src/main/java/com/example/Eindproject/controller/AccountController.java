package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountController {
    private final CustomerService service;

    public AccountController(CustomerService service){
        this.service = service;
    }

    @GetMapping("/add-customer")
    public String add(Model model){
        model.addAttribute("customer", new CustomerDto());

        return "customer/add-customer";
    }

    @PostMapping("/add-customer")
    public String add(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "customer/add-customer";
        }

        service.createCustomer(customerDto);
        return "customer/add-customer";
    }

    @GetMapping("/customers")
    public String showAllCustomers(Model model){
        model.addAttribute("customers", service.getAll());

        return "customer/customer";
    }
}
