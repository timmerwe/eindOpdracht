package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repos;

    public CustomerServiceImpl(CustomerRepository repos){
        this.repos = repos;
    }

    @Override
    public long createCustomer(CustomerDto cust) {
        Customer c = CustomerMapper.fromDtoToEntity(cust);
        repos.save(c);
        return c.getId();
    }

    @Override
    public ArrayList<CustomerDto> getAll() {
        ArrayList<CustomerDto> allCustomerDto = new ArrayList<>();
        List<Customer> allCustomers = repos.findAll();
        for (Customer customer : allCustomers) {
            CustomerDto dto = CustomerMapper.fromEntityToDto((customer));
            allCustomerDto.add(dto);
        }
        return allCustomerDto;
    }

}
