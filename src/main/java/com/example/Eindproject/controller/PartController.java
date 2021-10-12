package com.example.Eindproject.controller;

import com.example.Eindproject.dto.PartDto;
import com.example.Eindproject.entity.Part;
import com.example.Eindproject.repos.PartRepository;
import com.example.Eindproject.service.PartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService service;

    private PartController(PartService service){
        this.service = service;
    }

    @PostMapping("/add-part")
    public long addPart(@RequestBody PartDto part){
        return service.createPart(part);
    }

    @GetMapping("/get-part/{id}")
    public PartDto getPart( @PathVariable long id){
        return service.getPart(id);
    }

}
