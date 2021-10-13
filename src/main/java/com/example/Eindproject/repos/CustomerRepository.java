package com.example.Eindproject.repos;

import com.example.Eindproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    @Modifying
//    @Query("UPDATE Customer c SET c.firstName = :firstname, c.lastName = :lastName, c.email = :email, c.postalCode = :postalCode, c.address = :address, c.city = :city WHERE c.id = :customerId")
//    int updateCustomer(@Param("customerId") Long customerId, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("postalCode") String postalCode, @Param("address") String address, @Param("city") String city);
}
