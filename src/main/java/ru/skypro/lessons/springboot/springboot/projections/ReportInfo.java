package ru.skypro.lessons.springboot.springboot.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportInfo {
    private String positionName;
    private Long numbersOfEmployees;
    private Integer maxSalary;
    private Integer minSalary;
    private Double averageSalary;
}
