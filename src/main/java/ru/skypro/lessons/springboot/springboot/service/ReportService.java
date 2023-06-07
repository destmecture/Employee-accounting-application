package ru.skypro.lessons.springboot.springboot.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface ReportService {
    Integer addReport();
    ResponseEntity<Resource> downloadFile(Integer id);
}
