package com.gray.hospital.controller;

import com.gray.hospital.service.AdminReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminReportService adminReportService;

    public AdminController(AdminReportService adminReportService){
        this.adminReportService = adminReportService;
    }

    @GetMapping("/report")
    public Map<String,Object> getReport(){
        return adminReportService.generateReport();
    }
}