package ru.skypro.lessons.springboot.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springboot.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

   private final EmployeeRepository employeeRepository;



    @Override
    public void addSomeEmployees(List<Employee> list) {
        employeeRepository.getAllEmployees().addAll(list);
    }

    @SneakyThrows
    @Override
    public void refactorEmployeeById(Employee employee, Integer id) {
        Employee employeeForDelete = employeeRepository.getAllEmployees().stream()
                .filter(p->p.getId()==id).findFirst().orElseThrow(()-> new IdNotFoundException("Сотрудника по данному не найдено"));
        employeeRepository.getAllEmployees().remove(employeeForDelete);
        employeeRepository.getAllEmployees().add(employee);
    }


    @SneakyThrows
    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = employeeRepository.getAllEmployees().stream()
                .filter(p->p.getId()==id).findFirst().orElseThrow(()-> new IdNotFoundException("Сотрудника по данному не найдено"));
        return employee;
    }

    @SneakyThrows
    @Override
    public void deleteEmployeeById(Integer id) {
        Employee employee = employeeRepository.getAllEmployees().stream()
                .filter(p->p.getId()==id).findFirst().orElseThrow(()-> new IdNotFoundException("Сотрудника по данному не найдено"));
        employeeRepository.getAllEmployees().remove(employee);
    }

    @Override
    public List<Employee> moreThanDefinedSalary(int definedSalary) {
        return employeeRepository.getAllEmployees().stream()
                .filter(n->n.getSalary()>definedSalary)
                .toList();
    }
    
}
