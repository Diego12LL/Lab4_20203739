package com.example.lab4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "job_history")
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_id",nullable = false)
    private Integer idHistorial;

    @Column(name = "employee_id",nullable = false)
    private String idEmpleado;

    @Column(name = "start_date",nullable = false)
    private Date startDate;
    @Column(name = "end_date",nullable = false)
    private Date endDate   ;

    @Column(name = "department_id",nullable = false)
    private Integer iddepartamento;

}

