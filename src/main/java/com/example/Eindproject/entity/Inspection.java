package com.example.Eindproject.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "inspection")
    private List<Finding> findings;

    public Date getPlannedDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(plannedDate.toString());
        sdf.applyPattern("dd-MM-yyyy");
        return sdf.parse(sdf.format(d));
    }
}
