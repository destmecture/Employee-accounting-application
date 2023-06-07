package ru.skypro.lessons.springboot.springboot.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resources;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;

import ru.skypro.lessons.springboot.springboot.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;

import ru.skypro.lessons.springboot.springboot.projections.EmployeeView;
import ru.skypro.lessons.springboot.springboot.repository.EmployeeRepository;

import java.awt.print.Pageable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(result::add);

        return result.stream().map(EmployeeDTO::fromEmployee).toList();
    }


    @Override
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeDTO.toEmployee();

        employeeRepository.save(employee);
    }


    @Override
    public void refactorEmployeeById(EmployeeDTO employeeDTO, Integer id) {
        Employee employee = employeeDTO.toEmployee();
        employee.setId(id);
        employeeRepository.save(employee);
    }




    @SneakyThrows
    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Сотрудника по данному не найдено"));

        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> moreThanDefinedSalary(Integer definedSalary) {
         return employeeRepository.findBySalaryGreaterThan(definedSalary)
                .stream()
                .map(EmployeeDTO::fromEmployee)
                .toList();
    }

    @Override
    public List<EmployeeView> getEmployeeWithHighestSalary(){
        List<EmployeeView> employeeViewList = new ArrayList<>();

        int max = employeeRepository.findAllEmployeeView().stream()
                .max(Comparator.comparingInt(EmployeeView::getSalary)).get().getSalary();
        employeeRepository.findAllEmployeeView().stream()
                .filter(x->x.getSalary()==max).forEach(employeeViewList::add);
        return  employeeViewList;
    }
    @Override
    public List<EmployeeView> getEmployeesOnPosition(String positionInfo){
        if(positionInfo==null||positionInfo.isBlank())return employeeRepository.findAllEmployeeView();
        try{
            Integer a  = Integer.parseInt(positionInfo);
            return employeeRepository.findByPositionId(a);

        }catch (NumberFormatException e){
            return employeeRepository.findByPositionName(positionInfo);
        }
    }

    @Override
    public List<EmployeeDTO> getEmployeeWithPaging(Integer pageIndex, int unitPerPage) {
        if(unitPerPage>10)unitPerPage=10;
        if(pageIndex==null)pageIndex=0;

        PageRequest employeeOfConcretePage = PageRequest.of(pageIndex, unitPerPage);
        Page<Employee> page = employeeRepository.findAll(employeeOfConcretePage);
        return page.stream().map(EmployeeDTO::fromEmployee).toList();
    }


    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile file){
        if(file!=null){
            ObjectMapper objectMapper = new ObjectMapper();

            List<EmployeeDTO> employeeDTOList =
                    objectMapper.readValue(file.getInputStream(), new TypeReference<List<EmployeeDTO>>(){});

            employeeRepository.saveAll(
                    employeeDTOList.stream().map(EmployeeDTO::toEmployee).toList());

        }else{
            System.out.println("Файл не найден");
        }

    }
}
