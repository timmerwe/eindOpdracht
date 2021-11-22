package com.example.Eindproject.service;

import com.example.Eindproject.dto.FindingDto;

import java.util.ArrayList;

public interface FindingService {
    public long createFinding(FindingDto dto);
    public ArrayList<FindingDto> getAllFindingsById(Long id);
}
