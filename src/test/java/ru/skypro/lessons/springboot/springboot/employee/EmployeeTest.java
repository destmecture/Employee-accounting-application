package ru.skypro.lessons.springboot.springboot.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.springboot.controller.InfoController;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.exceptions.IdNotFoundException;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.repository.EmployeeRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static ru.skypro.lessons.springboot.springboot.service.EmployeeServiceImplTestConstants.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@AutoConfigureMockMvc
@WithMockUser(roles = {"ADMIN", "USER"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeTest {

    @Autowired
     MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
    }

    @Test
    void getListOfAllEmployee_whenTwoEmployeeWasAdded_expectedNotEmptyJsonList() throws Exception {
        mockMvc.perform(get("/user/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getEmployeeFullInfo_withCorrectId_expectedOneEmployee() throws Exception {
        int id = CORRECT_ID;

        mockMvc.perform(get("/user/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Alex"));
    }
    @Test
    void getEmployeeFullInfo_withUncorrectedId_expectedIdNotFoundException() throws Exception {
        int id = UNCORRECTED_ID;

        mockMvc.perform(get("/user/employee/{id}/fullInfo", id))
                .andExpect(status().isBadRequest());

    }
    @Test
    void getListOfEmployees_withSalaryLessThanAllEmployeesSalary_expectedJsonListWithTwoEmployee() throws Exception{
        mockMvc.perform(get("/user/employee/salaryHigherThan?salary="+CORRECT_SALARY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
    @Test
    void getListOfEmployees_withSalaryMoreThanAllEmployeesSalary_expectedEmptyJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/salaryHigherThan?salary="+BIG_SALARY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
    @Test
    void getListOfEmployeeInfo_withHighestSalary_expectedOneEmployeeInJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/EmployeeWithHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alex"));
    }


    @Test
    void getListOfEmployeeInfo_withNoPositionDefined_expectedTwoEmployeeInJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getListOfEmployeeInfo_withIntPositionDefined_expectedOneEmployeeInJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/?position=" +CORRECT_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alex"));
    }

    @Test
    void getListOfEmployeeInfo_withStringPositionDefined_expectedOneEmployeeInJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/?position=Driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Alex"));
    }

    @Test
    void getListOfEmployeeDtoPaging_withOneEmployeeOnEveryPage_expectedOneEmployeeIvanOnSecondPageInJsonList() throws Exception{
        mockMvc.perform(get("/user/employee/page?page=1&unitPerPage=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Ivan"));
    }


    @Test
    void deleteEmployeeById_withDefinedId_expectedOkStatusAndNoEmployeeByDeletedId() throws Exception{
        mockMvc.perform(delete("/admin/employee/" + CORRECT_ID))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/employee/1/fullInfo"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void refactorEmployeeById_withDefinedIdAndNewName_expectedOkStatusAndNewEmployeeName() throws Exception{
        int id = CORRECT_ID;
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(EMPLOYEE_TEST_ONE);

        mockMvc.perform(put("/admin/employee/" + id)
                        .content(objectMapper.writeValueAsString(employeeDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



        mockMvc.perform(get("/user/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value("Test Employee One"));


    }

    @Test
    void addNewEmployee_withEmployeeDto_expectedThirdEmployeeInListOfAll() throws Exception{
        EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(EMPLOYEE_TEST_ONE_WITHOUT_ID);
        mockMvc.perform(get("/user/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        mockMvc.perform(post("/admin/employee/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3));



    }

    @Test
    void addEmployeeInDataBaseByJson() throws Exception {


        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                "file.json",
                APPLICATION_JSON_VALUE,
                objectMapper.writeValueAsBytes(EMPLOYEE_DTO_TEST_LIST_WITHOUT_ID)
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/employee/upload")
                        .file(multipartFile)
                        .contentType(MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4));

    }

}

