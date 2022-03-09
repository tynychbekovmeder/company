package com.controllers;

import com.model.Course;
import com.model.Teacher;
import com.service.CompanyService;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping("/get_all/{id}")
    public String getAll(Model model, @PathVariable Long id) {
        model.addAttribute("courses", courseService.getAllCourse(id));
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/course/get_all_course";
    }

    @GetMapping("/add_course/{id}")
    public String addCourse(Model model, @PathVariable Long id) {
        model.addAttribute("course", new Course());
        model.addAttribute("company", companyService.getCompanyById(id));
        return "course/add_course";
    }

    @PostMapping("/save_course/{id}")
    public String saveCourse(@ModelAttribute Course course,
                             @PathVariable Long id) {
        courseService.save(course, id);
        return "redirect:/course/get_all/{id}";
    }

    @DeleteMapping("/delete_course/{id}")
    public String delete(@PathVariable Long id) {
        Long companyId = courseService.getCourseById(id).getCompany().getId();
        courseService.deleteCourse(id);
        return "redirect:/course/get_all/"+companyId;
    }

    @GetMapping("/update_course/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "/course/update_course";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Course course) {
        Long companyId = courseService.getCourseById(id).getCompany().getId();
        courseService.updateCourse(id, course);
        return "redirect:/course/get_all/"+companyId;
    }

    @GetMapping("main/{id}")
    public String main(
                       @ModelAttribute("course") Course course,
                       @ModelAttribute("teacher") Teacher teacher) {
        return "redirect:/teacher/get_all/{id}";
    }
}
