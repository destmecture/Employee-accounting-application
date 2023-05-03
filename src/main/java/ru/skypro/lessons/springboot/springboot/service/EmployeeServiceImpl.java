package ru.skypro.lessons.springboot.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

   private final EmployeeRepository employeeRepository;

    @Override
    public String salarySum() {
        return "Сумма всех зарплат равна = "+
         employeeRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Employee minSalary() {
        return employeeRepository.getAllEmployees().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @Override
    public Employee maxSalary() {
        return employeeRepository.getAllEmployees().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @Override
    public List<Employee> moreThanAverageSalary() {
        int average = (int) employeeRepository.getAllEmployees()
                .stream()
                .mapToInt(Employee::getSalary)
                        .average().orElse(0);
        return employeeRepository.getAllEmployees().stream()
                .filter(n->n.getSalary()>average)
                .toList();
    }
}
