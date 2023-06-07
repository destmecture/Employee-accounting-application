package ru.skypro.lessons.springboot.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.springboot.exceptions.PositionIdNotFoundExceptions;
import ru.skypro.lessons.springboot.springboot.pojo.Report;
import ru.skypro.lessons.springboot.springboot.repository.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;
    @SneakyThrows
    public Integer addReport(){
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytesForProjections = objectMapper.writeValueAsBytes(reportRepository.getReportInfo());
        return reportRepository.save(new Report(null, bytesForProjections)).getReport_id();
    }

    @SneakyThrows
    public ResponseEntity<Resource> downloadFile(Integer id){
        if(!reportRepository.existsById(id))
            throw  new PositionIdNotFoundExceptions("Position not found");
        String fileName = "Position Report";
        Resource resource = new ByteArrayResource(reportRepository.findById(id).get().getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ fileName + id + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);

    }

}
