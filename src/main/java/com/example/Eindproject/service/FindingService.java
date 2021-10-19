package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.FindingDto;
import com.example.Eindproject.entity.Finding;

import java.util.ArrayList;

public interface FindingService {
    public long createFinding(FindingDto dto);
    public FindingDto getFinding(long id);
    public ArrayList<FindingDto> getAllFindingsById(Long id);
}
