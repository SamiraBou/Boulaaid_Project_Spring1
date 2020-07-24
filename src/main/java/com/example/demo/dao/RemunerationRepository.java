package com.example.demo.dao;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Remuneration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemunerationRepository extends JpaRepository<Remuneration, Long> {
    public Page<Remuneration> findByNomContains(String searchStr, Pageable pageable);
}
