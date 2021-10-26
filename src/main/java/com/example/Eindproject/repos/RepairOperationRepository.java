package com.example.Eindproject.repos;

import com.example.Eindproject.entity.Finding;
import com.example.Eindproject.entity.RepairOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairOperationRepository extends JpaRepository<RepairOperation, Long> {
    List<RepairOperation> findAllByRepair_Id(Long id);

}
