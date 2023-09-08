package com.example.lab4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id",nullable = false)
    private Integer idEmpleado;

    @Column(name = "first_name",nullable = false,length =20)
    private String nombre;

    @Column(name = "last_name",nullable = false, length = 25)
    private String apellido;

    @Column(name = "email",nullable = false, length = 25)
    private String email;

    @Column(name = "phone_number",nullable = false, length = 20)
    private String numero  ;

    @Column(name = "department_id",nullable = false)
    private Integer iddepartamento;

    @Column(name = "job_id",nullable = false, length = 10)
    private String puesto;

    @Column(name = "salary",nullable = false)
    private Double salario  ;
}

