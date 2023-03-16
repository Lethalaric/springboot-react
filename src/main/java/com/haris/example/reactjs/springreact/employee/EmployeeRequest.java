package com.haris.example.reactjs.springreact.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class EmployeeRequest {
    private String username;
    private String fullName;
    private String role;
    private int numberOfExperience;

    public static Employee to(EmployeeRequest employeeRequest) {
        String firstName = "";
        String lastName = "";
        if (employeeRequest.getFullName() != null && !employeeRequest.getFullName().isBlank()) {
            String[] nameSplit = employeeRequest.getFullName().trim().split(" ");
            if (nameSplit.length > 1) {
                lastName = employeeRequest.getFullName().substring(employeeRequest.getFullName().indexOf(" ") + 1);
            }
            firstName = nameSplit[0];
        }
        return Employee.builder()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withRole(employeeRequest.role)
                .withUsername(employeeRequest.getUsername())
                .withNumberOfExperience(employeeRequest.getNumberOfExperience())
                .build();
    }
}
