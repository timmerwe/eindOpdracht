package com.example.Eindproject.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// Definitie van de inspectie entity met hierbij de getters, setters en constuctors gegenereerd door lombok
// De inspectie is gekoppeld aan één auto, kan 0 of meerdere bevindingen hebben en kan gekoppeld worden aan 0 of meerdere reparaties

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    @ManyToOne()
    @JoinColumn(name="car_id")
    private Car car;

    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date plannedDate;

    @NonNull
    String status;

    @Nullable
    int wantsRepair = 2;

    @OneToMany(mappedBy = "inspection")
    private List<Finding> findings;

    @OneToMany(mappedBy = "inspection")
    private List<Repair> repair;

    public Date getPlannedDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(plannedDate.toString());
        sdf.applyPattern("dd-MM-yyyy");
        return sdf.parse(sdf.format(d));
    }

    boolean finished = false;
}
