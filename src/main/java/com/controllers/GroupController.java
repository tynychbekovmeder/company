package com.controllers;

import com.model.Course;
import com.model.Group;
import com.model.Student;
import com.service.CourseService;
import com.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final CourseService courseService;

    @GetMapping("/get_all")
    public String getAll(Model model) {
        List<Group> groups = groupService.getAllGroup();
        model.addAttribute("groups", groups);
        return "/group/get_all_group";
    }

    @GetMapping("/add_group")
    public String addCourse(Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("courses", courseService.findAll());
        return "group/add_group";
    }

    @PostMapping("/save_group")
    public String saveCourse(@ModelAttribute Group group) {
        groupService.save(group, 1L);
        Long companyId = null;
        for (Course course : group.getCourseList()) {
            companyId = course.getCompany().getId();
        }
        return "redirect:/group/get_all/" + companyId;
    }

    @DeleteMapping("/delete_group/{id}")
    public String delete(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return "redirect:/group/get_all/{id}";
    }

    @GetMapping("/update_group/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getGroupById(id));
        return "/group/update_group";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/company";
    }

    @GetMapping("/mainPage/{id}")
    public String main(@ModelAttribute("group") Group group,
                       @ModelAttribute("student") Student student) {
        return "redirect:/student/get_all/{id}";
    }
}
