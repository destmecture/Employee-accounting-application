package ru.skypro.lessons.springboot.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springboot.pojo.Employee;
import ru.skypro.lessons.springboot.springboot.pojo.Position;
import ru.skypro.lessons.springboot.springboot.projections.EmployeeView;


import java.util.List;
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, CrudRepository<Employee, Integer>   {
    List<Employee> findBySalaryGreaterThan(Integer definedSalary);
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<EmployeeView> findAllEmployeeView();
    @Query(value = "SELECT e FROM Employee e JOIN FETCH Position p ON e.position = p " +
            "WHERE e.position.position_id = :id")
    List<EmployeeView> findByPositionId(@Param("id")Integer id);
    @Query(value = "SELECT e FROM Employee e JOIN FETCH Position p ON e.position = p " +
            "WHERE e.position.position_name = :name")
    List<EmployeeView> findByPositionName(@Param("name")String name);


}
