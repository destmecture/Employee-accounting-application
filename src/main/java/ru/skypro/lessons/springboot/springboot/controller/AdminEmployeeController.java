package ru.skypro.lessons.springboot.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.service.EmployeeService;

@RestController
@RequestMapping("/admin/employee")
@RequiredArgsConstructor
public class AdminEmployeeController {
    private final EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Integer id){
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/{id}")
    public void refactorEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer id){
        employeeService.refactorEmployeeById(employeeDTO, id);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file){
        employeeService.uploadFile(file);
    }

    @GetMapping("/gettext")
    public String getTestText(){
        String test = "text";
        return test;
    }
}
