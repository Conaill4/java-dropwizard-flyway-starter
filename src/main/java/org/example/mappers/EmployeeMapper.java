package org.example.mappers;

import org.example.models.Employee;
import org.example.models.EmployeeResponse;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static List<EmployeeResponse> mapEmployeeListToEmployeeResponseList(List<Employee> employees) {
        return employees
                .stream()
                .map(employee -> new EmployeeResponse(employee.getEmployeeId(), employee.getEmployeeName(), employee.getSalary(), employee.getBankAccountNumber(), employee.getNationalInsuranceNumber()))
                .collect(Collectors.toList());
    }
}
