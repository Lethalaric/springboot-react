package com.haris.example.reactjs.springreact.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class EmployeeResponse {
    private String fullName;

    private String role;

    private int numberOfExperience;

    private String username;

    public static EmployeeResponse from(Employee employee) {
        return EmployeeResponse.builder()
                .withFullName(String.format("%s %s", employee.getFirstName(), employee.getLastName()))
                .withUsername(employee.getUsername())
                .withRole(employee.getRole())
                .withNumberOfExperience(employee.getNumberOfExperience())
                .build();
    }
}
