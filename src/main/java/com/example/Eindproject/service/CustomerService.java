package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    public long createCustomer(CustomerDto cust);
    public ArrayList<CustomerDto> getAll();
}
