package com.adb.Sgm.requetsDTO;

import com.adb.Sgm.model.User;
import com.adb.Sgm.model.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
    public RegisterDTO(User user){
        this(user.getEmail(), user.getPassword(), user.getRole());
    }
}
