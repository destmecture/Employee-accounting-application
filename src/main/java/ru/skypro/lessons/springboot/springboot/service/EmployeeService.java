package ru.skypro.lessons.springboot.springboot.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.dto.PositionDTO;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeView;

import java.util.List;

public interface EmployeeService {


    List<EmployeeDTO> getAllEmployees();

    void addEmployee(EmployeeDTO employeeDTO);
    void refactorEmployeeById(EmployeeDTO employeeDTO, Integer id);
    EmployeeDTO getEmployeeById(Integer id);
    void deleteEmployeeById(Integer id);
    List<EmployeeDTO> moreThanDefinedSalary(Integer definedSalary);
    List<EmployeeView> getEmployeeWithHighestSalary();
    List<EmployeeView> getEmployeesOnPosition(String positionInfo);
    List<EmployeeDTO> getEmployeeWithPaging(Integer pageIndex, int unitPerPage);
    void uploadFile(MultipartFile file);

}
