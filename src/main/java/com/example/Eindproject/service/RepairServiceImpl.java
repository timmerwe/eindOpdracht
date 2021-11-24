package com.example.Eindproject.service;

import com.example.Eindproject.dto.CustomerDto;
import com.example.Eindproject.dto.RepairDto;
import com.example.Eindproject.entity.Car;
import com.example.Eindproject.entity.Customer;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.entity.Repair;
import com.example.Eindproject.mapping.CustomerMapper;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.CustomerRepository;
import com.example.Eindproject.repos.InspectionRepository;
import com.example.Eindproject.repos.RepairRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService{

    private final RepairRepository repos;
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;
    private final CustomerRepository customerRepository;

    // initializeren van repositoryies
    public RepairServiceImpl(RepairRepository repos, CarRepository carRepository, InspectionRepository inspectionRepository, CustomerRepository customerRepository){
        this.repos = repos;
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
        this.customerRepository = customerRepository;
    }

//    Maakt een nieuwe reparatie aan en koppeld deze aan een auto, vervolgens word deze opgeslagen in de database.
    @Override
    public void createRepair(RepairDto repairDto) throws ParseException {
        Car c = carRepository.getById(repairDto.getCarId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(repairDto.getPlannedDate());
        sdf.applyPattern("yyyy-MM-dd");
        Date newDateString = sdf.parse(sdf.format(date));

        Repair r = new Repair(repairDto.getStatus(), repairDto.getArrangements(), newDateString, c);

        if(repairDto.getInspectionId() != null){
            Inspection i = inspectionRepository.getById(repairDto.getInspectionId());
            r.setInspection(i);
        }

        repos.save(r);

    }

//    Haalt een reparatie op basis van het id
    @Override
    public RepairDto getRepair(Long id) throws ParseException {
        Repair r = repos.getById(id);
        Long inspection = null;
        if(r.getInspection() != null){
            inspection = r.getInspection().getId();
        }
        return new RepairDto(r.getId(), r.getStatus(), r.getArrangements(), r.getPlannedDate().toString(), inspection, r.getCar().getId(), r.getCar().getLicencePlate());
    }

//    Haalt een lijst op van alle reparaties waarvan de status 'Ingepland' of 'In uitvoering' is.
    @Override
    public ArrayList<RepairDto> getAllRepair() throws ParseException {
        ArrayList<RepairDto> allRepairsDto = new ArrayList<>();
        List<Repair> allRepairs = repos.findAllByStatusAndPickupApointment("Ingepland", false);
        allRepairs.addAll(repos.findAllByStatusAndPickupApointment("In uitvoering", false));
        for (Repair r : allRepairs) {
            Long inspection = null;
            if(r.getInspection() != null){
                inspection = r.getInspection().getId();
            }
            RepairDto dto = new RepairDto(r.getId(), r.getStatus(), r.getArrangements(), r.getPlannedDate().toString(), inspection, r.getCar().getId(), r.getCar().getLicencePlate());
            allRepairsDto.add(dto);
        }
        return allRepairsDto;
    }

//    Veranderd de status van een reparatie
    @Override
    public void changeRepairStatus(Long id, String status) {
        Optional<Repair> rOptional = repos.findById(id);
        if (rOptional.isPresent()){
            Repair r = rOptional.get();
            r.setStatus(status);
            repos.save(r);
        }
    }

//    Zet finished op true zodat de reparatie niet meer zichtbaar is in de frontend
    @Override
    public void setToFinished(Long id) {
        Optional<Repair> rOptional = repos.findById(id);
        if (rOptional.isPresent()){
            Repair r = rOptional.get();
            r.setPickupApointment(true);
            repos.save(r);
        }
    }

//    haalt alle reparaties op op basis van status en de afhaalafspraak. Vervolgens worden bij de reparaties de bijbehorende klanten opgehaald en deze in 1 matrix array opgeslagen.
    @Override
    public Object[][] getAllRepairsByStatus(String status, boolean pickupApointment) throws ParseException {
        List<Repair> allRepairs = repos.findAllByStatusAndPickupApointment(status, pickupApointment);
        Object[][] repairsObj = new Object[allRepairs.size()][2];
        int index = 0;
        for (Repair r : allRepairs) {
            Long inspection = null;
            if(r.getInspection() != null){
                inspection = r.getInspection().getId();
            }
            RepairDto dto = new RepairDto(r.getId(), r.getStatus(), r.getArrangements(), r.getPlannedDate().toString(), inspection, r.getCar().getId(), r.getCar().getLicencePlate());;
            Customer c = customerRepository.getById(r.getCar().getCustomer().getId());
            CustomerDto cDto = CustomerMapper.fromEntityToDto(c);
            repairsObj[index][0] = dto;
            repairsObj[index][1] = cDto;
            index++;
        }
        return repairsObj;
    }
}
