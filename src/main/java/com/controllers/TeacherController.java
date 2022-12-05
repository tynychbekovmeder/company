package com.controllers;

import com.model.Teacher;
import com.service.CourseService;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

   private final TeacherService teacherService;
   private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/get_all/{id}")
    public String get(Model model, @PathVariable Long id) {
        model.addAttribute("teachers", teacherService.getAllTeacher());
        model.addAttribute("course",courseService.getCourseById(id));
        return "/teacher/get_all_teacher";
    }

    @GetMapping("/add_teacher/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("teacher", new Teacher());
        model.addAttribute("course", courseService.getCourseById(id));
        return "teacher/add_teacher";
    }

    @PostMapping("/save_teacher/{courseId}")
    public String add(@ModelAttribute Teacher teacher,
                      @PathVariable Long courseId){
        System.out.println("SAVING NEW TEACHER: " + teacher.toString());
            teacherService.save(teacher, courseId);
        return "redirect:/teacher/get_all/{courseId}";
    }

    @DeleteMapping("/delete_teacher/{id}")
    public String delete(@PathVariable Long id){
        System.out.println(id);
        teacherService.deleteTeacher(id);
        return "redirect:/company";
    }

    @GetMapping("/update_teacher/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "/teacher/update_teacher";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute Teacher teacher){
        teacherService.updateTeacher(id, teacher);
        return "redirect:/company";
    }

}
