package com.example.studentmgr.web;

import com.example.studentmgr.domain.User;
import com.example.studentmgr.repo.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final UserRepository repo;

    public ProfileController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping({ "/", "/profile" })
    public String profile(@AuthenticationPrincipal UserDetails me, Model model) {
        User u = repo.findByUsername(me.getUsername()).orElseThrow();
        model.addAttribute("user", u);
        return "profile";
    }
}
