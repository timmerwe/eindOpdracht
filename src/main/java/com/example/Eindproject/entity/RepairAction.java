package com.example.Eindproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class RepairAction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Date timestamp;

    double price;

    @ManyToOne()
    @JoinColumn(name="repair_id")
    private Repair repair;
}
