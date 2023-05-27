package ru.skypro.lessons.springboot.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EmployeeDTO {
    private Integer id;
    private String name;
    private Integer salary;
    private PositionDTO positionDTO;

    public static EmployeeDTO fromEmployee(Employee employee){
        return new EmployeeDTO()
                .setId(employee.getId())
                .setName(employee.getName())
                .setSalary(employee.getSalary())
                .setPositionDTO(PositionDTO.fromPosition(employee.getPosition()));
    }
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.getPositionDTO().toPosition());
        return employee;
    }


}
