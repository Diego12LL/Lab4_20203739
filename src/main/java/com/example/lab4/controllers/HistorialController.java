package com.example.lab4.controllers;

import com.example.lab4.entity.Employees;
import com.example.lab4.repository.EmployeeRepository;
import com.example.lab4.repository.HistorialRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HistorialController {
    final HistorialRepository historialRepository;


    public HistorialController(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @GetMapping(value = {"/historial"})
    public String listaHistorial(Model model) {
        model.addAttribute("listaHistorial", historialRepository.findAll());
        return "lab4/listaHistorial";
    }
}
