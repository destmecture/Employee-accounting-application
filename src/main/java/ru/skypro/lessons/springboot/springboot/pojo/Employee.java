package ru.skypro.lessons.springboot.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class Employee {
    private String name;
    private int salary;
}
