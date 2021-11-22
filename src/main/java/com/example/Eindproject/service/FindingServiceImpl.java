package com.example.Eindproject.service;

import com.example.Eindproject.dto.FindingDto;
import com.example.Eindproject.entity.Finding;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.mapping.FindingMapper;
import com.example.Eindproject.repos.FindingRepository;
import com.example.Eindproject.repos.InspectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindingServiceImpl implements FindingService{

    private final FindingRepository repos;
    private final InspectionRepository inspectionRepository;

    public FindingServiceImpl(FindingRepository repos, InspectionRepository inspectionRepository){
        this.repos = repos;
        this.inspectionRepository = inspectionRepository;
    }

    @Override
    public long createFinding(FindingDto dto) {
        Inspection i = inspectionRepository.getById(dto.getInspection());
        Finding f = FindingMapper.fromDtoToEntity(dto, i);
        repos.save(f);
        return f.getId();
    }

    @Override
    public ArrayList<FindingDto> getAllFindingsById(Long id) {
        ArrayList<FindingDto> allFindingDto = new ArrayList<>();
        List<Finding> allFindings = repos.findAllByInspection_id(id);
        for (Finding f : allFindings) {
            FindingDto dto = FindingMapper.fromEntityToDto(f);
            allFindingDto.add(dto);
        }
        return allFindingDto;
    }
}
