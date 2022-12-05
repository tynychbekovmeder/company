package com.controllers;

import com.model.Company;
import com.model.Course;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }


    @GetMapping
    public String get(Model model) {
        model.addAttribute("companies", service.getAllCompany());
        return "/company/get_all_company";
    }

    @GetMapping("/add_company")
    public String view(Model model) {
        model.addAttribute("company", new Company());
        return "company/add_company";
    }

    @PostMapping("/save_company")
    public String add(Company company) {
        System.out.println("NEW COMPANY: " + company.toString());
        service.save(company);
        return "redirect:/company";
    }

    @DeleteMapping("/delete_company/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteCompany(id);
        return "redirect:/company";
    }

    @GetMapping("/update_company/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("company", service.getCompanyById(id));
        return "/company/update_company";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Company company) {
        service.updateCompany(id, company);
        return "redirect:/company";
    }

    @GetMapping("/mainPage/{id}")
    public String main(@ModelAttribute("company") Company company,
                       @ModelAttribute("course") Course course) {
        return "redirect:/course/get_all/{id}";
    }
}
