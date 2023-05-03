package ru.skypro.lessons.springboot.springboot.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
     private final List<Employee> employeeList = List.of(
             new Employee("Ivan", 50_000),
             new Employee("Alexandr", 65_000),
             new Employee("Svetlana", 57_000),
             new Employee("Ekaterina", 52_000));



    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }
}
