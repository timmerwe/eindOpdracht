package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Part;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CustomerRepository;
import org.springframework.stereotype.Service;

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
    public CustomerDto getCustomer(long id) {
        return null;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerDto> allCustomerDto = null;
        List<Customer> allCustomers = repos.findAll();
        for (Customer customer : allCustomers) {
            allCustomerDto.add(CustomerMapper.fromEntityToDto(customer));
        }
        return allCustomerDto;
    }
}
