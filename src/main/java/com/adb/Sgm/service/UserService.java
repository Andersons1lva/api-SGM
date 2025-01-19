package com.adb.Sgm.service;

import com.adb.Sgm.model.User;
import com.adb.Sgm.model.UserRole;
import com.adb.Sgm.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password, UserRole role) {
        if (usersRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword, role);
        return usersRepository.save(user);
    }
}