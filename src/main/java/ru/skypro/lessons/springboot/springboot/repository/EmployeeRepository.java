package ru.skypro.lessons.springboot.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springboot.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeInfo;


import java.util.List;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer>   {
    List<Employee> findBySalaryGreaterThan(Integer definedSalary);
    @Query(value = "SELECT e FROM Employee e")
    List<EmployeeDTO> findAllEmployeeDTO();
    @Query(value = "SELECT e FROM Employee e")
    List<EmployeeInfo> findAllEmployeeView();
    @Query(value = "SELECT e FROM Employee e JOIN FETCH Position p ON e.position = p " +
            "WHERE e.position.position_id = :id")
    List<EmployeeInfo> findByPositionId(@Param("id")Integer id);
    @Query(value = "SELECT e FROM Employee e JOIN FETCH Position p ON e.position = p " +
            "WHERE e.position.position_name = :name")
    List<EmployeeInfo> findByPositionName(@Param("name")String name);

}
