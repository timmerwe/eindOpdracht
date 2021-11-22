package com.example.Eindproject.repos;

import com.example.Eindproject.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    List<Inspection> findAllByCar_id(Long car_id);
    List<Inspection> findAllByWantsRepairAndFinished(int wantsRepair, boolean finished);
}
