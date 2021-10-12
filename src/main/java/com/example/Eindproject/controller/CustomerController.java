package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @PostMapping("/add-customer")
    public long createCustomer(@RequestBody CustomerDto cust){
        return service.createCustomer(cust);
    }
}
