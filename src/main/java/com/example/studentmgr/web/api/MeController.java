package com.example.studentmgr.web.api;

import com.example.studentmgr.domain.User;
import com.example.studentmgr.repo.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MeController {
    private final UserRepository repo;
    public MeController(UserRepository repo){this.repo=repo;}

    @GetMapping("/me")
    public Map<String,Object> me(@AuthenticationPrincipal UserDetails me){
        User u = repo.findByUsername(me.getUsername()).orElseThrow();
        return Map.of("username", u.getUsername(), "fullName", u.getFullName(), "email", u.getEmail(), "role", u.getRole());
    }
}
