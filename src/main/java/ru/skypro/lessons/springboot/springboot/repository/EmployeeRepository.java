package ru.skypro.lessons.springboot.springboot.repository;

import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
}
