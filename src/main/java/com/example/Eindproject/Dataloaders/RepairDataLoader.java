package com.example.Eindproject.Dataloaders;

import com.example.Eindproject.entity.CarAction;
import com.example.Eindproject.entity.CarPart;
import com.example.Eindproject.entity.Repair;
import com.example.Eindproject.entity.RepairOperation;
import com.example.Eindproject.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
@Order(4)
@Slf4j
@Transactional
public class RepairDataLoader implements CommandLineRunner {
    private final RepairRepository repairRepository;
    private final RepairOperationRepository repairOperationRepository;
    private final CarRepository carRepository;
    private final InspectionRepository inspectionRepository;
    private final CarPartRepository carPartRepository;
    private final CarActionRepository carActionRepository;

    public RepairDataLoader(RepairRepository repairRepository, RepairOperationRepository repairOperationRepository, CarRepository carRepository, InspectionRepository inspectionRepository, CarPartRepository carPartRepository, CarActionRepository carActionRepository) {
        this.repairRepository = repairRepository;
        this.repairOperationRepository = repairOperationRepository;
        this.carRepository = carRepository;
        this.inspectionRepository = inspectionRepository;
        this.carPartRepository = carPartRepository;
        this.carActionRepository = carActionRepository;
    }

    //    In deze functie voeg ik een aantal reparaties toe aan de vooraf gecreëerde autos in customerDataLoader. Vervolgens voeg ik een aantal reparatie handelingen en onderdelen toe aan de reparaties, hierbij maak ik gebruik van een aantal vooraf gecreëerde onderdelen en handelingen uit repairPartsDataLoader.
    @Override
    public void run(String... args) throws Exception {
        ArrayList<Repair> repairs = new ArrayList<>();
        ArrayList<RepairOperation> repairOperations = new ArrayList<>();
        ArrayList<CarPart> parts = new ArrayList<>();
        ArrayList<CarAction> actions = new ArrayList<>();

        repairs.add(new Repair("Ingepland",  "De auto moet binnen 3 dagen klaar zijn", createDate("20/11/2021"), carRepository.getById(7L)));
        repairs.add(new Repair("Ingepland",  "100 euro korting op de gehele reparatie", createDate("19/11/2021"), carRepository.getById(5L)));
        repairs.add(new Repair("Voltooid",  "100 euro korting op de gehele reparatie", createDate("19/11/2021"), carRepository.getById(5L)));
        repairs.add(new Repair("Voltooid",  "100 euro korting op de gehele reparatie", createDate("19/11/2021"), carRepository.getById(5L)));

        repairs.get(3).setPickupApointment(true);

        repairs.get(0).setInspection(inspectionRepository.getById(11L));

        repairRepository.saveAll(repairs);

        actions.addAll(carActionRepository.findAllByCustomAction(false));
        parts.addAll(carPartRepository.findAll());

        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(0).getPrice() + parts.get(0).getPrice(), repairs.get(0), actions.get(0), parts.get(0)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(1).getPrice() + parts.get(1).getPrice(), repairs.get(0), actions.get(1), parts.get(1)));
        repairOperations.add(new RepairOperation(createDate("23/11/2021"), actions.get(2).getPrice() + parts.get(2).getPrice(), repairs.get(0), actions.get(2), parts.get(2)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(2).getPrice() + parts.get(2).getPrice(), repairs.get(1), actions.get(2), parts.get(2)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(3).getPrice() + parts.get(3).getPrice(), repairs.get(1), actions.get(3), parts.get(3)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(3).getPrice() + parts.get(3).getPrice(), repairs.get(2), actions.get(3), parts.get(3)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(3).getPrice() + parts.get(3).getPrice(), repairs.get(3), actions.get(3), parts.get(3)));
        repairOperations.add(new RepairOperation(createDate("22/11/2021"), actions.get(2).getPrice() + parts.get(2).getPrice(), repairs.get(3), actions.get(2), parts.get(2)));

        repairOperationRepository.saveAll(repairOperations);

    }

    public Date createDate(String dateString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(dateString);

        return date;
    }
}
