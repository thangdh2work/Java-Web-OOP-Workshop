package com.example.studentmgr.service;

import com.example.studentmgr.domain.Role;
import com.example.studentmgr.domain.User;
import com.example.studentmgr.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public List<User> findAllStudents() {
        return repo.findAll().stream().filter(u -> u.getRole() == Role.STUDENT).toList();
    }

    public User findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public User createStudent(User u) {
        if (repo.existsByUsername(u.getUsername()))
            throw new IllegalArgumentException("Username exists");
        u.setRole(Role.STUDENT);
        u.setPassword(encoder.encode(u.getPassword()));
        return repo.save(u);
    }

    public User updateStudent(Long id, User upd) {
        User u = findById(id);
        u.setFullName(upd.getFullName());
        u.setEmail(upd.getEmail());
        u.setEnabled(upd.isEnabled());
        if (org.springframework.util.StringUtils.hasText(upd.getPassword())) {
            u.setPassword(encoder.encode(upd.getPassword()));
        }
        return repo.save(u);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
