package com.adb.Sgm.controller;

import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.UserRepository;
import com.adb.Sgm.requetsDTO.UserDTO;
import com.adb.Sgm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO registerDTO){
        userService.registerUser(registerDTO.email(), registerDTO.password(), registerDTO.role());
        return ResponseEntity.ok().build();
    }
}
