package com.example.studentmgr.data;

import com.example.studentmgr.domain.Role;
import com.example.studentmgr.domain.User;
import com.example.studentmgr.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner seed(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("123456"));
                admin.setFullName("Administrator");
                admin.setEmail("admin@minnn.tech");
                admin.setRole(Role.ADMIN);
                repo.save(admin);
            }
        };
    }
}
