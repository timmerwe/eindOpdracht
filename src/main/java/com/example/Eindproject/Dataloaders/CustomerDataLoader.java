package com.example.Eindproject.Dataloaders;

import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Order(1)
@Slf4j
@Transactional
public class CustomerDataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerDataLoader(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

//    In deze functie maak ik een aantal gebruikers met daaraan gekoppelde auto's aan, zodat het systeem altijd gevuld is met data
    @Override
    public void run(String... args) throws Exception {

        Customer cust1 = new Customer("Tom", "Coronel", "tomcoronel@gmail.com", "0637283746", "1234AB", "Dorpstraat 1", "Apeldoorn");
        Customer cust2 = new Customer("Henk", "Jan", "henkjan@gmail.com", "0683927332", "4321XY", "Bergweg 199", "Rotterdam");
        Customer cust3 = new Customer("Patricha", "Paai", "patricha@gmail.com", "0638282623", "1423JE", "straat 1", "Amsterdam");

        customerRepository.save(cust1);
        customerRepository.save(cust2);
        customerRepository.save(cust3);

        Car c1 = new Car("AB-123-C", "Audi");
        Car c2 = new Car("DE-321-F", "Volvo");
        Car c3 = new Car("GH-987-I", "Vokswagen");
        Car c4 = new Car("JK-789-L", "Porsche");

        c1.setCustomer(cust1);
        c2.setCustomer(cust2);
        c3.setCustomer(cust2);
        c4.setCustomer(cust3);

        carRepository.save(c1);
        carRepository.save(c2);
        carRepository.save(c3);
        carRepository.save(c4);


    }
}
