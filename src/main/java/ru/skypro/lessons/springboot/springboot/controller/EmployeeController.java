package ru.skypro.lessons.springboot.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/salary/sum")
    public String salarySum() {
        return employeeService.salarySum();
    }

    @GetMapping("/salary/min")
    public Employee minSalary() {
        return employeeService.minSalary();
    }

    @GetMapping("/salary/max")
    public Employee maxSalary(){
        return employeeService.maxSalary();
    }

    @GetMapping("/high-salary")
    public List<Employee> moreThanAverageSalary(){
        return employeeService.moreThanAverageSalary();
    }



}
