package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Part;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CustomerDto getCustomer(long id) {
        Customer c = repos.getById(id);
        return CustomerMapper.fromEntityToDto(c);

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

    @Override
    public void editCustomer(long id, CustomerDto c) {
//        repos.updateCustomer(id, c.getFirstName(), c.getLastName(), c.getEmail(), c.getPostalCode(), c.getAddress(), c.getCity());
    }
}
