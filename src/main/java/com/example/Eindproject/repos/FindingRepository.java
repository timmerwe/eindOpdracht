package com.example.Eindproject.repos;

import com.example.Eindproject.entity.Finding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FindingRepository extends JpaRepository<Finding, Long> {

    List<Finding> findAllByInspection_id(Long id);
}
