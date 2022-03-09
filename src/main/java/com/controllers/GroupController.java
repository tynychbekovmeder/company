package com.controllers;

import com.model.Group;
import com.model.Student;
import com.service.CompanyService;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/group")
public class GroupController {

   private final GroupService groupService;
   private final CompanyService companyService;

    @Autowired
    public GroupController(GroupService groupService, CompanyService companyService) {
        this.groupService = groupService;
        this.companyService = companyService;
    }

    @GetMapping("/get_all/{id}")
    public String getAll(Model model, @PathVariable Long id) {
        model.addAttribute("groups", groupService.getAllGroup());
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/group/get_all_group";
    }

    @GetMapping("/add_group/{id}")
    public String addCourse(Model model,@PathVariable Long id) {
        model.addAttribute("group", new Group());
        model.addAttribute("company",companyService.getCompanyById(id));
        return "group/add_group";
    }

    @PostMapping("/save_group/{id}")
    public String saveCourse( @ModelAttribute Group group,
                              @PathVariable Long id) {
        groupService.save(group, id);
        return "redirect:/group/get_all/{id}";
    }

    @DeleteMapping("/delete_group/{id}")
    public String delete(@PathVariable Long id){
        groupService.deleteGroup(id);
        return "redirect:/group/get_all/";
    }

    @GetMapping("/update_group/{id}")
    public String update(@PathVariable Long id, Model model){
        model.addAttribute("group", groupService.getGroupById(id));
        return "/group/update_group";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute Group group){
        groupService.updateGroup(id,group);
        return "redirect:/company";
    }

    @GetMapping("/mainPage/{id}")
    public String main( @ModelAttribute("group") Group group,
                        @ModelAttribute("student") Student student) {
        return "redirect:/student/get_all/{id}";
    }
}
