package com.adb.Sgm.controller;
import com.adb.Sgm.infra.security.TokenService;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/sessions")
public class SessionsController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody User userCredentials) {
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();
        // Busca o usuário pelo email
        User user = (User) usersRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>() {{ put("message", "E-mail e/ou senha incorreta"); }});
        }
        // Verifica se a senha está correta
        boolean passwordMatched = BCrypt.checkpw(password, user.getPassword());
        if (!passwordMatched) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>() {{ put("message", "E-mail e/ou senha incorreta"); }});
        }

        // Gera o token usando o TokenService
        String token = tokenService.generateToken(user);

        // Retorna a resposta com o token e informações do usuário
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}