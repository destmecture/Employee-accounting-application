package ru.skypro.lessons.springboot.springboot.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
     private List<Employee> employeeList = new ArrayList<>(List.of(
             new Employee(1,"Ivan", 50_000),
             new Employee(2,"Alexandr", 65_000),
             new Employee(3,"Svetlana", 57_000),
             new Employee(4,"Ekaterina", 52_000)));





    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }




}
