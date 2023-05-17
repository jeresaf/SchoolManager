package com.yukon.schoolmanager.SchoolManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/teachers")
    public String teachers() {
        return "teachers";
    }

    @GetMapping("/students")
    public String students() {
        return "students";
    }
    
}
