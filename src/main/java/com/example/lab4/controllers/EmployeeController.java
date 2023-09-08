package com.example.lab4.controllers;

import com.example.lab4.entity.Employees;
import com.example.lab4.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    final EmployeeRepository employeeRepository;


    public EmployeeController( EmployeeRepository employeeRepository) {
        this.employeeRepository =employeeRepository ;
    }
    @GetMapping(value = { "/employees"})
    public String listaEmployees(Model model) {
        model.addAttribute("listaEmployees", employeeRepository.findAll());

        return "lab4/listaEmployees";
    }

    @PostMapping("/buscarPorNombreApellido")
    public String buscarPorNombreApellido(@RequestParam("searchField") String searchField, Model model) {

        //List<Shipper> lista =  shipperRepository.findByCompanyName(searchField);
        List<Employees> lista = employeeRepository.buscarPorNombreApellido(searchField);
        model.addAttribute("listaEmployees", lista);
        model.addAttribute("textoBuscado", searchField);

        return "lab4/listaEmployees";
    }

    @GetMapping("/editPhoneSalary")
    public String editarPhoneSalary(Model model, @RequestParam("id") int id) {

        Optional<Employees> optionalEmployees = employeeRepository.findById(id);

        if (optionalEmployees.isPresent()) {
            Employees employee = optionalEmployees.get();
            model.addAttribute("employee", employee);
            return "lab4/editFrmPhoneSalary";
        } else {
            return "redirect:/employees";
        }
    }

    @PostMapping("/updatePhoneSalary")
    public String updatePhoneSalary(Employees employee) {
        employeeRepository.actualizarTelefonoSalario(employee.getNumero(), employee.getSalario(), employee.getIdEmpleado());

        return "redirect:/employees";
    }

}