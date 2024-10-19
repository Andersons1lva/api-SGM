package com.adb.Sgm.controller;

import com.adb.Sgm.dtos.UserResponseDTO;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.UserRepository;
import com.adb.Sgm.requetsDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


//    @CrossOrigin(origins = "*",allowedHeaders = "*")
//    @PostMapping
//    public void saveUser(@RequestBody UserRequestDTO data){
//        User userData = new User(data);
//        userRepository.save(userData);
//        return;
//    }

//    @CrossOrigin(origins = "*",allowedHeaders = "*")
//    @GetMapping
//    public List<UserResponseDTO> getAll(){
//        List<UserResponseDTO> userList = userRepository.findAll().stream().map(UserResponseDTO::new).toList();
//        return userList;
//    }
}
