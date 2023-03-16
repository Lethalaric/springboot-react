package com.haris.example.reactjs.springreact.config;

import com.haris.example.reactjs.springreact.employee.Employee;
import com.haris.example.reactjs.springreact.employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader implements CommandLineRunner {
    private EmployeeRepository employeeRepository;

    public DatabaseLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadEmployeeData();
    }

    private void loadEmployeeData() {
        this.employeeRepository.save(
                Employee.builder()
                        .withFirstName("Mukhtar")
                        .withLastName("Haris")
                        .withRole("Java Developer")
                        .withUsername("mukhtarharis")
                        .withNumberOfExperience(3)
                        .build()
        );
    }
}
