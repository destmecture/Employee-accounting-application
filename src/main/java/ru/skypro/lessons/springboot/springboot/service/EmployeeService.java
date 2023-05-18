package ru.skypro.lessons.springboot.springboot.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    void addSomeEmployees(List<Employee> list);
    void refactorEmployeeById(Employee employee, Integer id);
    Employee getEmployeeById(Integer id);
    void deleteEmployeeById(Integer id);
    List<Employee> moreThanDefinedSalary(int definedSalary);

}
