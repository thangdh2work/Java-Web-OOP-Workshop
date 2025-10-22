package com.example.studentmgr.web;

import com.example.studentmgr.domain.User;
import com.example.studentmgr.repo.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public PasswordController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo; this.encoder = encoder;
    }

    @GetMapping("/password/change")
    public String changeForm() {
        return "password/change";
    }

    @PostMapping("/password/change")
    public String change(@AuthenticationPrincipal UserDetails me,
                         @RequestParam("oldPassword") String oldPassword,
                         @RequestParam("newPassword") String newPassword,
                         @RequestParam("confirmPassword") String confirmPassword,
                         Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorKey", "pwd.mismatch");
            return "password/change";
        }
        User u = repo.findByUsername(me.getUsername()).orElseThrow();
        if (!encoder.matches(oldPassword, u.getPassword())) {
            model.addAttribute("errorKey", "pwd.invalidOld");
            return "password/change";
        }
        u.setPassword(encoder.encode(newPassword));
        repo.save(u);
        model.addAttribute("successKey", "pwd.success");
        return "password/change";
    }
}
