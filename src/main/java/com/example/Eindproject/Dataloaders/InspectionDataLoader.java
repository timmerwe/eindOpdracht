package com.example.Eindproject.Dataloaders;

import com.example.Eindproject.entity.Finding;
import com.example.Eindproject.entity.Inspection;
import com.example.Eindproject.repos.CarRepository;
import com.example.Eindproject.repos.FindingRepository;
import com.example.Eindproject.repos.InspectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
@Order(2)
@Slf4j
@Transactional
public class InspectionDataLoader implements CommandLineRunner {
    private final InspectionRepository inspectionRepository;
    private final FindingRepository findingRepository;
    private final CarRepository carRepository;

    public InspectionDataLoader(InspectionRepository inspectionRepository, FindingRepository findingRepository, CarRepository carRepository) {
        this.inspectionRepository = inspectionRepository;
        this.findingRepository = findingRepository;
        this.carRepository = carRepository;
    }

//    In deze functie voeg ik een aantal inspecties toe aan de vooraf gecreëerde autos in customerDataLoader. Vervolgens zet ik een aantal bevindigen in deze inspecties.
    @Override
    public void run(String... args) throws Exception {
        ArrayList<Inspection> inspections = new ArrayList<>();
        ArrayList<Finding> findings = new ArrayList<>();

        inspections.add(new Inspection(carRepository.getById(4L), createDate("22/11/2021"), "In uitvoering"));
        inspections.add(new Inspection(carRepository.getById(6L), createDate("28/11/2021"), "Ingepland"));
        inspections.add(new Inspection(carRepository.getById(5L), createDate("18/11/2021"), "Afgekeurd"));
        inspections.add(new Inspection(carRepository.getById(7L), createDate("18/11/2021"), "Afgekeurd"));

        inspections.get(2).setWantsRepair(0);
        inspections.get(3).setWantsRepair(1);

        inspectionRepository.saveAll(inspections);


        findings.add(new Finding("Olie lekt", "Olie lekt bij de caberateur", inspections.get(0)));
        findings.add(new Finding("Band lek", "De rechter voorband is lek", inspections.get(0)));
        findings.add(new Finding("Remschijven", "Alle remschijven moeten worden vervangen", inspections.get(0)));
        findings.add(new Finding("Barst in ruit", "Barst in vooruit", inspections.get(1)));
        findings.add(new Finding("Zuiger vervangen", "één van de zuigers lekt", inspections.get(1)));
        findings.add(new Finding("Barst in ruit", "Barst in vooruit", inspections.get(2)));
        findings.add(new Finding("Zuiger vervangen", "één van de zuigers lekt", inspections.get(2)));
        findings.add(new Finding("Barst in ruit", "Barst in vooruit", inspections.get(3)));
        findings.add(new Finding("Zuiger vervangen", "één van de zuigers lekt", inspections.get(3)));

        findingRepository.saveAll(findings);

    }

    public Date createDate(String dateString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(dateString);

        return date;
    }
}
