package com.example.demo;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoulaaidProjectApplication implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    public static void main(String[] args) {
        SpringApplication.run(BoulaaidProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* Employee e = new Employee();
        e.setFirstName("ss");
        e.setLastName("bb");
        e.setSalary(12345);
        employeeRepository.save(e);
        for (Employee employee : employeeRepository.findAll()) {
            System.out.println(employee.getId());
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getSalary());
        }*/
    }
}
