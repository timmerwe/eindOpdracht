package com.example.Eindproject.repos;

import com.example.Eindproject.entity.CarAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarActionRepository extends JpaRepository<CarAction, Long> {
    List<CarAction> findAllByCustomAction(Boolean customAction);

}
