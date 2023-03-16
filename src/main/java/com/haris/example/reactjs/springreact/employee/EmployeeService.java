package com.haris.example.reactjs.springreact.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse getEmployee(String username) {
        Employee employee = employeeRepository.findByUsername(username);

        return EmployeeResponse.from(employee);
    }

    public List<EmployeeResponse> getEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();

        return employees.stream().map(EmployeeResponse::from).collect(Collectors.toList());
    }

    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) throws Exception {

        boolean isEmployeeExistsByUserName = employeeRepository.existsByUsername(employeeRequest.getUsername());

        if (isEmployeeExistsByUserName) {
            throw new Exception("Username already exists");
        }

        Employee employee = EmployeeRequest.to(employeeRequest);

        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findByUsername(employee.getUsername());
        log.debug("savedEmployee : " + savedEmployee);
        return EmployeeResponse.from(savedEmployee);
    }
}
