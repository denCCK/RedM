package com.example.Redm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("title", "Отчёты");
        return "reports";
    }
}
