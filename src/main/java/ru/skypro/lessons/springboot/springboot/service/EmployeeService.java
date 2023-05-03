package ru.skypro.lessons.springboot.springboot.service;

import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    String salarySum();
    Employee minSalary();
    Employee maxSalary();
    List<Employee> moreThanAverageSalary();

}
