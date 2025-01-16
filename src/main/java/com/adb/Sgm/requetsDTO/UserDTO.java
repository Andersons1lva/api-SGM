package com.adb.Sgm.requetsDTO;


import com.adb.Sgm.model.UserRole;

public record UserDTO(String email, String password, UserRole role) {
}
