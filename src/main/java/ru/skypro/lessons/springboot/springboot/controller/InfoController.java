package ru.skypro.lessons.springboot.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/all")
public class InfoController {

    @Value("${app.env}")
    private String env;

    @GetMapping("/appInfo")
    public String getAppInfo(){
        return env;
    }
}
