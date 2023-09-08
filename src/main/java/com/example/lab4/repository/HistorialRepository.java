package com.example.lab4.repository;

import com.example.lab4.entity.Employees;
import com.example.lab4.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistorialRepository extends JpaRepository<History, Integer> {
}
