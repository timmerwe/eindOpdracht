package com.example.Eindproject.service;

import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.entity.Part;

public interface PartService {
    public long createPart(PartDto part);
    public PartDto getPart(long id);
}
