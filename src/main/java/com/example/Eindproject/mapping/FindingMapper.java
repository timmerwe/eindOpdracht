package com.example.Eindproject.mapping;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.FindingDto;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Finding;
import com.example.Eindproject.entity.Inspection;

public class FindingMapper {
    public static Finding fromDtoToEntity (FindingDto dto, Inspection i) {
        return new Finding(dto.getTitle(), dto.getDescription(), i);
    }

    public static FindingDto fromEntityToDto (Finding f) {
        return new FindingDto(f.getId(), f.getTitle(), f.getDescription(), f.getInspection().getId());
    }

}
