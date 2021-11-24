package com.example.Eindproject.Customer;

import com.example.Eindproject.dto.CarDto;
import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CustomerRepository;
import com.example.Eindproject.service.CustomerService;
import com.example.Eindproject.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;


    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;


    @Test
    void shouldGetAllCustomers(){
        customerServiceImpl.getAll();
        verify(customerRepository).findAll();
    }

    @Test
    void shouldAddCustomer(){
        Customer c = createCustomer();

        when(customerRepository.save(any(Customer.class))).thenReturn(c);
        long i = customerServiceImpl.createCustomer(CustomerMapper.fromEntityToDto(c));

        assertEquals(c.getId(), i);
    }


    private Customer createCustomer(){
        Customer c = new Customer();
        c.setId(0);
        c.setFirstName("Tom");
        c.setLastName("Tiedeman");
        c.setEmail("Tom@tiedeman.nl");
        c.setPhoneNumber("06382729");
        c.setPostalCode("1234AB");
        c.setAddress("Dropstraat 1");
        c.setCity("Apeldoorn");
        return c;
    }


}
