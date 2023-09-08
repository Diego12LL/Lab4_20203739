package com.example.lab4.repository;

import com.example.lab4.entity.Employees;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM employees where first_name like %?1% or last_name like %?1%")
    List<Employees> buscarPorNombreApellido(String textoIngreso);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET phone_number = ?1 , salary= ?2 WHERE employee_id = ?3")
    void actualizarTelefonoSalario(String numero, Double salario, int idEmpleado);
}
