package ru.skypro.lessons.springboot.springboot.projections;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeInfo {


    private String name;
    private int salary;

}
