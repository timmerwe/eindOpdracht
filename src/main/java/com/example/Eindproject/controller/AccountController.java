package com.example.Eindproject.controller;

import com.example.Eindproject.dto.CustomerDto;
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
public class AccountController {
    private final CustomerService service;
    private final InspectionService inspectionService;
    private final RepairService repairService;

    public AccountController(CustomerService service, InspectionService inspectionService, RepairService repairService){
        this.service = service;
        this.inspectionService = inspectionService;
        this.repairService = repairService;
    }

    @GetMapping("/administration/add-customer")
    public String add(Model model){
        model.addAttribute("customer", new CustomerDto());

        return "customer/add-customer";
    }

    @PostMapping("/administration/add-customer")
    public String add(@Valid @ModelAttribute("customer") CustomerDto customerDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "customer/add-customer";
        }

        service.createCustomer(customerDto);
        return "redirect:/administration/customers";
    }

    @GetMapping("/administration/customers")
    public String showAllCustomers(Model model){
        model.addAttribute("customers", service.getAll());

        return "customer/customer";
    }

    @GetMapping("/administration/call-list")
    public String showCallList(Model model) throws ParseException {
        model.addAttribute("inspections", inspectionService.getAllInspectionsByWantsRepair());
        model.addAttribute("repairs", repairService.getAllRepairsByStatus("Voltooid", false));

        return "customer/call-list";
    }

    @GetMapping("/cashier/payment")
    public String showPaymentList(Model model) throws ParseException {
        model.addAttribute("repairs", repairService.getAllRepairsByStatus("Voltooid", true));

        return "cashier/pickup";
    }

    @GetMapping("/cashier/payment/changeStatus/{id}")
    public String changePaymentStatus(Model model, @PathVariable("id") Long repairId) throws ParseException {
        repairService.changeRepairStatus(repairId, "Betaald");

        return "redirect:/cashier/payment";
    }
}
