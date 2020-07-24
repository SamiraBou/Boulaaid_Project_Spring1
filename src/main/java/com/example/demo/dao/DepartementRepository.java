package com.example.demo.dao;

import com.example.demo.entities.Departement;

import com.example.demo.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    public Page<Departement> findByNomContains(String searchStr, Pageable pageable);
}
