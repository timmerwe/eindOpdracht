package com.example.Eindproject.Dataloaders;

import com.example.Eindproject.entity.CarAction;
import com.example.Eindproject.entity.CarPart;
import com.example.Eindproject.repos.CarActionRepository;
import com.example.Eindproject.repos.CarPartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
@Order(3)
@Slf4j
@Transactional
public class RepairPartsDataLoader implements CommandLineRunner {
    private final CarPartRepository carPartRepository;
    private final CarActionRepository carActionRepository;

    public RepairPartsDataLoader(CarPartRepository carPartRepository, CarActionRepository carActionRepository) {
        this.carPartRepository = carPartRepository;
        this.carActionRepository = carActionRepository;
    }

//    In deze functie voeg ik een aantal reparatie onderdelen en handelingen toe aan de database
    @Override
    public void run(String... args) throws Exception {
        ArrayList<CarPart> parts = new ArrayList<>();
        ArrayList<CarAction> actions = new ArrayList<>();

        parts.add(new CarPart("Koplamp", 25, "1AFEMA45JE"));
        parts.add(new CarPart("V-Snaar", 60, "83A32OE"));
        parts.add(new CarPart("Olie", 20, "129AFJE3"));
        parts.add(new CarPart("Zuiger", 25, "164AB3"));

        carPartRepository.saveAll(parts);

       actions.add(new CarAction("Vervangen koplamp", "kapotte koplamp vervangen voor een nieuwe", 60, false));
       actions.add(new CarAction("Vervangen V-Snaar", "Oude V-Snaar vervangen voor een nieuwe", 180, false));
       actions.add(new CarAction("Olie vervangen", "Oude olie vervangen voor nieuwe", 50, false));
       actions.add(new CarAction("Zuiger vervangen", "De oude zuiger eruit halen en een nieuwe monteren", 240, false));

       carActionRepository.saveAll(actions);
    }
}
