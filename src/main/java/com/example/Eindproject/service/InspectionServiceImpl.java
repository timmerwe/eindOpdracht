package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.InspectionDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.mapping.InspectionMapper;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import com.example.Eindproject.repos.InspectionRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InspectionServiceImpl implements InspectionService {

    private final InspectionRepository repos;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;


    // initializeren van repositoryies
    public InspectionServiceImpl(InspectionRepository repos, CarRepository carRepository, CustomerRepository customerRepository){
        this.repos = repos;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

//    Maakt een nieuwe inspectie aan en koppeld deze aan een auto, vevolgens word deze opgeslagen in de database.
    @Override
    public Long createInspection(InspectionDto dto) throws ParseException {
        Car c = carRepository.getById(dto.getCarId());

        Inspection i = InspectionMapper.fromDtoToEntity(dto, c);

        repos.save(i);
        return i.getId();

    }

//    Haal één isnspectie op op basis van het inspectie id
    @Override
    public InspectionDto getInspection(Long id) throws ParseException {
        Inspection i = repos.getById(id);
        return InspectionMapper.fromEntityToDto(i);
    }


//    Haal een lijst op met alle inspecties waarbij nog niet aangegeven is of er een reparatie moet plaatsvinden
    @Override
    public ArrayList<InspectionDto> getAllInspections() throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAllByWantsRepairAndFinished(2, false);
        for (Inspection i : allInspections) {
            InspectionDto dto = InspectionMapper.fromEntityToDto(i);
            allInspectionsDto.add(dto);
        }
        return allInspectionsDto;
    }

//    Haal een lijst op met alle inspecties per auto
    @Override
    public ArrayList<InspectionDto> getAllInspectionsByCarId(Long id) throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAllByCar_id(id);
        for (Inspection i : allInspections) {
            InspectionDto dto = InspectionMapper.fromEntityToDto(i);
            allInspectionsDto.add(dto);
        }
        return allInspectionsDto;
    }

//    Verranderd de status van een inspectie
    @Override
    public void changeInspectionStatus(Long id, String status) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setStatus(status);
            repos.save(i);
        }
    }

//    Zet finished op true zodat de inspectie uit de frondend van het systeem word gehaald
    @Override
    public void setToFinished(Long id) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setFinished(true);
            repos.save(i);
        }
    }

//    Stelt in of de auto gerepareerd mag worden of niet
    @Override
    public void changeInspectionWantsRepair(Long id, int wantsRepair) {
        Optional<Inspection> iOptional = repos.findById(id);
        if (iOptional.isPresent()){
            Inspection i = iOptional.get();
            i.setWantsRepair(wantsRepair);
            repos.save(i);
        }
    }

//    Haalt een lijst op met alle inspecties waarbij de auto gerepareerd mag worden
    @Override
    public Object[][] getAllInspectionsByWantsRepair() throws ParseException {
        ArrayList<InspectionDto> allInspectionsDto = new ArrayList<>();
        List<Inspection> allInspections = repos.findAllByWantsRepairAndFinished(0, false);
        Object[][] inspectionObj = new Object[allInspections.size()][2];
        int index = 0;
        for (Inspection i : allInspections) {
            InspectionDto dto = InspectionMapper.fromEntityToDto(i);
            Customer c = customerRepository.getById(i.getCar().getCustomer().getId());
            CustomerDto cDto = CustomerMapper.fromEntityToDto(c);
            inspectionObj[index][0] = dto;
            inspectionObj[index][1] = cDto;
            index++;
        }
        return inspectionObj;
    }
}
