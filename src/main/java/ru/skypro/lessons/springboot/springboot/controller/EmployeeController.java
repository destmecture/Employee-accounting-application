package ru.skypro.lessons.springboot.springboot.controller;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }
    @GetMapping("/salaryHigherThan")
    public List<Employee> moreThanDefinedSalary(@RequestParam("salary") int definedSalary){
        return employeeService.moreThanDefinedSalary(definedSalary);
    }
    @PutMapping("/{id}")
    public void refactorEmployeeById(@RequestBody Employee employee, @PathVariable Integer id){
        employeeService.refactorEmployeeById(employee, id);
    }

    @PostMapping("/")
    public void addSomeEmployees(@RequestBody List<Employee> list){
        employeeService.addSomeEmployees(list);
    }





}
