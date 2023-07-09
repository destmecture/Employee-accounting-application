package ru.skypro.lessons.springboot.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeInfo;
import ru.skypro.lessons.springboot.springboot.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/user/employee")
@RequiredArgsConstructor
public class UserEmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/all")
    public List<EmployeeDTO> getEmployees(){
        return  employeeService.getAllEmployees();
    }


    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }


    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> moreThanDefinedSalary(@RequestParam("salary") Integer definedSalary){
        return employeeService.moreThanDefinedSalary(definedSalary);
    }

    @GetMapping("/EmployeeWithHighestSalary")
    public List<EmployeeInfo> getEmployeeWithHighestSalary(){
        return employeeService.getEmployeeWithHighestSalary();

    }
    @GetMapping("/")
    public List<EmployeeInfo> getEmployeesOnPosition(@RequestParam(value = "position", required = false) String positionInfo){
        return employeeService.getEmployeesOnPosition(positionInfo);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") Integer pageIndex, int unitPerPage){
        return employeeService.getEmployeeWithPaging(pageIndex, unitPerPage);
    }

}
