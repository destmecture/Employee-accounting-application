package ru.skypro.lessons.springboot.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeInfo;
import ru.skypro.lessons.springboot.springboot.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.skypro.lessons.springboot.springboot.service.EmployeeServiceImplTestConstants.*;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repositoryMock;

    @InjectMocks
    private EmployeeServiceImpl out;


    public static Stream<Arguments> argumentsForReturningAllEmployeesByPosition() {
        return Stream.of(
                Arguments.of("   "),
                Arguments.of( (String)null),
                Arguments.of("")
        );
    }



    @Test
    @DisplayName("Returned added employee and call repository once")
    public void shouldCallRepositoryMethodWhenAddEmployee(){
        when(repositoryMock.save(eq(EMPLOYEE_TEST_ONE)))
                .thenReturn(EMPLOYEE_TEST_ONE);

        out.addEmployee(EmployeeDTO.fromEmployee(EMPLOYEE_TEST_ONE));

        verify(repositoryMock, times(1)).save(EMPLOYEE_TEST_ONE);
    }

    @Test
    @DisplayName("Returned refactored employee and call repository once")
    public void refactorEmployeeById_ShouldCallRepository(){
        when(repositoryMock.save(eq(EMPLOYEE_TEST_ONE)))
                .thenReturn(EMPLOYEE_TEST_ONE);

        out.refactorEmployeeById(EmployeeDTO.fromEmployee(EMPLOYEE_TEST_ONE_WITHOUT_ID), CORRECT_ID);

        verify(repositoryMock, times(1)).save(EMPLOYEE_TEST_ONE);

    }

    @DisplayName("Does not return employee by id and throw IdNotFoundException, call repository once")
    @Test
    void getEmployeeByNotCorrectId_ShouldThrowExceptions() {
        assertThrows(IdNotFoundException.class, () -> out.getEmployeeById(UNCORRECTED_ID));
        verify(repositoryMock, times(1)).findById(UNCORRECTED_ID);
    }


    @DisplayName("Returned employee by id and call repository once")
    @Test
    void getEmployeeById_CorrectEmployeeAnyId_ShouldCallRepository() {
        EmployeeDTO expected = EmployeeDTO.fromEmployee(EMPLOYEE_TEST_ONE);

        when(repositoryMock.findById(eq(CORRECT_ID)))
                .thenReturn(Optional.of(EMPLOYEE_TEST_ONE));
        EmployeeDTO actual = out.getEmployeeById(CORRECT_ID);

        assertEquals(expected, actual);

        verify(repositoryMock, times(1)).findById(CORRECT_ID);

    }




    @DisplayName("Returned employees with salary bigger than coming and call repository once")
    @Test
    void getEmployeesWithSalaryMoreThan_CorrectEmployeesAnySum_ShouldCallRepository() {
        when(repositoryMock.findBySalaryGreaterThan(eq(CORRECT_SALARY)))
                .thenReturn(EMPLOYEE_TEST_LIST);

        List<EmployeeDTO> actual = out.moreThanDefinedSalary(CORRECT_SALARY);

        assertIterableEquals(EMPLOYEE_DTO_TEST_LIST, actual);

        verify(repositoryMock, times(1)).findBySalaryGreaterThan(anyInt());
    }

    @DisplayName("Call repository once before deleting employee")
    @Test
    void deleteById_ShouldCallRepository() {
        doNothing().when(repositoryMock).deleteById(CORRECT_ID);

        out.deleteEmployeeById(CORRECT_ID);

        verify(repositoryMock, times(1)).deleteById(CORRECT_ID);
    }

    @DisplayName("Get all employees and call repository once")
    @Test
    void findAllEmployeesDTO_ShouldCallRepositoryOnce() {
        when(repositoryMock.findAllEmployeeDTO())
                .thenReturn(EMPLOYEE_DTO_TEST_LIST);

        List<EmployeeDTO> actual = out.getAllEmployees();

        assertIterableEquals(EMPLOYEE_DTO_TEST_LIST, actual);

        verify(repositoryMock, times(1)).findAllEmployeeDTO();
    }

    @DisplayName("Returned max salary employees and call repository twice")
    @Test
    void getMaxSalaryEmployee_CorrectEmployees_ShouldCallRepository() {
        when(repositoryMock.findAllEmployeeView())
                .thenReturn(EMPLOYEE_INFO_TEST_LIST);

        List<EmployeeInfo> actual = out.getEmployeeWithHighestSalary();
        int max = EMPLOYEE_INFO_TEST_LIST.stream()
                .max(Comparator.comparingInt(EmployeeInfo::getSalary)).get().getSalary();

        assertIterableEquals(EMPLOYEE_INFO_TEST_LIST.stream().filter(x->x.getSalary()==max).toList(), actual);

        verify(repositoryMock, times(2)).findAllEmployeeView();

    }

    @DisplayName("Find all employees by position name and calls repository once")
    @Test
    void returnEmployeesByPosition_CorrectPositionName_ShouldCallRepositoryOnce() {
        List<EmployeeInfo> test = new ArrayList<>() {{
            add(EMPLOYEE_INFO_TEST_ONE);
        }};

        when(repositoryMock.findByPositionName(eq(POSITION_TEST_ONE.getPosition_name())))
                .thenReturn(test);


        List<EmployeeInfo> actualByName = out.getEmployeesOnPosition(POSITION_TEST_ONE.getPosition_name());


        assertIterableEquals(test,actualByName);

        verify(repositoryMock, times(1)).findByPositionName(POSITION_TEST_ONE.getPosition_name());
    }

    @DisplayName("Find all employees if position name wrong and calls repository once")
    @ParameterizedTest
    @MethodSource("argumentsForReturningAllEmployeesByPosition")
    void returnEmployeesByPosition_NotCorrectPositionName_ShouldCallRepositoryOnce(String testArguments) {
        when(repositoryMock.findAllEmployeeView())
                .thenReturn(EMPLOYEE_INFO_TEST_LIST);

        List<EmployeeInfo> actual = out.getEmployeesOnPosition(testArguments);

        assertIterableEquals(EMPLOYEE_INFO_TEST_LIST,actual);

        verify(repositoryMock, times(1)).findAllEmployeeView();
    }

    @DisplayName("Return first page by page number and call repository once")
    @Test
    void returnEmployeesByPageNumber_CorrectPageNumber_ShouldNCallRepositoryOnce() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(repositoryMock.findAll(eq(pageRequest)))
                .thenReturn(new PageImpl<>(EMPLOYEE_TEST_LIST));

        List<EmployeeDTO> expected = out.getEmployeeWithPaging(0, 10);

        assertEquals(EMPLOYEE_DTO_TEST_LIST, expected);

        verify(repositoryMock, times(1)).findAll(pageRequest);
    }

    @SneakyThrows
    @DisplayName("Upload employees file and call repository once")
    @Test
    void createEmployeesByJson_CorrectJson_ShouldNCallRepositoryOnce() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        json = objectMapper.writeValueAsString(EMPLOYEE_DTO_TEST_LIST);

        when(repositoryMock.saveAll(eq(EMPLOYEE_TEST_LIST)))
                .thenReturn(any());

        out.uploadFile(new MockMultipartFile("test",json.getBytes()));

        verify(repositoryMock, times(1)).saveAll(EMPLOYEE_TEST_LIST);

    }




}
