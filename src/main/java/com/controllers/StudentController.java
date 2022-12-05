package com.controllers;

import com.model.Group;
import com.model.Student;
import com.service.GroupService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService service, GroupService groupService) {
        this.studentService = service;
        this.groupService = groupService;
    }

    @GetMapping("/get_all/{id}")
    public String get(Model model, @PathVariable Long id) {
        model.addAttribute("students", studentService.getAllStudent());
        model.addAttribute("group", groupService.getGroupById(id));
        return "student/get_all_student";
    }

    @GetMapping("/add_student/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("student", new Student());
        model.addAttribute("group", groupService.getGroupById(id));
        return "student/add_student";
    }

    @PostMapping("/save_student/{groupId}")
    public String add(@ModelAttribute Student student,
                      @PathVariable Long groupId) {
        studentService.save(student,groupId);
        return "redirect:/student/get_all/{groupId}";
    }

    @DeleteMapping("/delete_student/{id}")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/company";
    }

    @GetMapping("/update_student/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "/student/update_student";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/company";
    }

    @GetMapping("/main{id}")
    public String main(@PathVariable Long id) {
        return "mainPage";
    }
}
