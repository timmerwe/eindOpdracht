package com.example.Eindproject.service;

import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.entity.Part;
import com.example.Eindproject.repos.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository repos;

    public PartServiceImpl(PartRepository repos){
        this.repos = repos;
    }

    @Override
    public long createPart(PartDto part) {
        Part p = new Part(part.getName(), part.getPrice(), part.getQuantity());
        repos.save(p);
        return p.getId();
    }

    @Override
    public PartDto getPart(long id) {
        Part p = repos.getById(id);
        return new PartDto(p);
    }
}
