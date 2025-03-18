package com.example.spring_security_api.init;

import com.example.spring_security_api.model.User;
import com.example.spring_security_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository repository;



    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (repository.findByUsername("admin") == null) {
            User user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("master123");
            user.getRoles().add("MANAGERS");
            repository.save(user);
        }

        if (repository.findByUsername("user") == null) {
            User user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("user123");
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }
}
