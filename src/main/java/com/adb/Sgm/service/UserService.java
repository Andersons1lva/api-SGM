package com.adb.Sgm.service;

import com.adb.Sgm.model.User;
import com.adb.Sgm.model.UserRole;
import com.adb.Sgm.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String password, UserRole role) {
        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(email, encodedPassword, role);
        return userRepository.save(user);
    }

    public User buscarPorEmail(String email) {
        return (User) userRepository.findByEmail(email);
    }
}