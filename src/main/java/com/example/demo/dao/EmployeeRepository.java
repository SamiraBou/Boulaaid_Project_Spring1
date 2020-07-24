package com.example.demo.dao;


import com.example.demo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Page<Employee> findByfirstNameContains(String searchStr, Pageable pageable);
}
