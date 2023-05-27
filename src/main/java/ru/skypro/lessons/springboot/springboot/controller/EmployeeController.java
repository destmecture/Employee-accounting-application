package ru.skypro.lessons.springboot.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.dto.PositionDTO;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeView;
import ru.skypro.lessons.springboot.springboot.service.EmployeeService;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;


    @GetMapping("/all")
    public List<EmployeeDTO> getEmployees(){
        return  employeeService.getAllEmployees();
    }


    @GetMapping("/{id}/fullInfo")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }
    @GetMapping("/salaryHigherThan")
    public List<EmployeeDTO> moreThanDefinedSalary(@RequestParam("salary") Integer definedSalary){
        return employeeService.moreThanDefinedSalary(definedSalary);
    }
    @PutMapping("/{id}")
    public void refactorEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id){
        employeeService.refactorEmployeeById(employeeDTO, id);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
    }
    @GetMapping("/EmployeeWithHighestSalary()")
    public List<EmployeeView> getEmployeeWithHighestSalary(){
        return employeeService.getEmployeeWithHighestSalary();

    }
    @GetMapping("/")
    public List<EmployeeView> getEmployeesOnPosition(@RequestParam(value = "position", required = false) String positionInfo){
        return employeeService.getEmployeesOnPosition(positionInfo);
    }
    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") Integer pageIndex, int unitPerPage){
        return employeeService.getEmployeeWithPaging(pageIndex, unitPerPage);
    }

}
