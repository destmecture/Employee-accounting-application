package ru.skypro.lessons.springboot.springboot.service;

import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.pojo.Position;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeInfo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImplTestConstants {
    public static final Position POSITION_TEST_ONE = new Position(1, "Test Position");
    public static final Position POSITION_TEST_TWO = new Position(2, "Test Position Two");


    public static final Employee EMPLOYEE_TEST_ONE = new Employee(1, "Test Employee One", 30_000, POSITION_TEST_ONE );
    public static final Employee EMPLOYEE_TEST_TWO = new Employee(2, "Test Employee Two", 20_000, POSITION_TEST_TWO);



    public static final List<Employee> EMPLOYEE_TEST_LIST = new ArrayList<>(){{
        add(EMPLOYEE_TEST_ONE);
        add(EMPLOYEE_TEST_TWO);
    }};
    public static final List<EmployeeInfo> EMPLOYEE_INFO_TEST_LIST = new ArrayList<>(){{
        add(new EmployeeInfo(EMPLOYEE_TEST_ONE.getName(), EMPLOYEE_TEST_ONE.getSalary()));
        add(new EmployeeInfo(EMPLOYEE_TEST_TWO.getName(),EMPLOYEE_TEST_TWO.getSalary()));
    }};

    public static final EmployeeInfo EMPLOYEE_INFO_TEST_ONE = new EmployeeInfo(EMPLOYEE_TEST_ONE.getName(), EMPLOYEE_TEST_ONE.getSalary());

    public static final List<EmployeeDTO> EMPLOYEE_DTO_TEST_LIST = EMPLOYEE_TEST_LIST.stream().map(EmployeeDTO::fromEmployee).toList();
    public static final Employee EMPLOYEE_TEST_ONE_WITHOUT_ID = new Employee(null,"Test Employee One", 30_000, POSITION_TEST_ONE);
    public static final Employee EMPLOYEE_TEST_TWO_WITHOUT_ID = new Employee(null,"Test Employee Two", 30_000, POSITION_TEST_TWO);
    public static final List<Employee> EMPLOYEE_TEST_LIST_WITHOUT_ID = new ArrayList<>(){{
        add(EMPLOYEE_TEST_ONE_WITHOUT_ID);
        add(EMPLOYEE_TEST_TWO_WITHOUT_ID);
    }};
    public static final List<EmployeeDTO> EMPLOYEE_DTO_TEST_LIST_WITHOUT_ID = EMPLOYEE_TEST_LIST_WITHOUT_ID.stream().map(EmployeeDTO::fromEmployee).toList();


    public static final int CORRECT_ID = 1;
    public static final int UNCORRECTED_ID = -1;

    public static final int CORRECT_SALARY = 10_000;
    public static final int BIG_SALARY = 60_000;





}
