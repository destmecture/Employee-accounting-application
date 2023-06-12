package ru.skypro.lessons.springboot.springboot.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.springboot.pojo.Report;
import ru.skypro.lessons.springboot.springboot.projections.ReportInfo;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Integer> {
    @Query("SELECT new ru.skypro.lessons.springboot.springboot.projections.ReportInfo " +
            "(p.position_name, COUNT(e), MAX(e.salary), MIN(e.salary), AVG(e.salary)) " +
            "FROM Position p JOIN Employee e GROUP BY p.position_name")
    List<ReportInfo> getReportInfo();
}
