package com.example.Eindproject.repos;

import com.example.Eindproject.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findAllByStatusAndPickupApointment(String status, boolean finished);

}
