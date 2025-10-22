package com.example.studentmgr.web.admin;

import com.example.studentmgr.domain.User;
import com.example.studentmgr.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/students")
public class AdminStudentController {
    private final UserService userService;
    public AdminStudentController(UserService userService) { this.userService = userService; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", userService.findAllStudents());
        return "admin/students/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("student", new User());
        return "admin/students/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("student") User student, BindingResult br) {
        if (br.hasErrors()) return "admin/students/form";
        userService.createStudent(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", userService.findById(id));
        return "admin/students/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("student") User student, BindingResult br) {
        if (br.hasErrors()) return "admin/students/form";
        userService.updateStudent(id, student);
        return "redirect:/admin/students";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        userService.deleteStudent(id);
        return "redirect:/admin/students";
    }
}
