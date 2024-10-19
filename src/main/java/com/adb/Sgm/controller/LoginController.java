package com.adb.Sgm.controller;

import com.adb.Sgm.infra.security.TokenService;
import com.adb.Sgm.model.AuthenticationDTO;
import com.adb.Sgm.model.LoginResponseDTO;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.UserRepository;
import com.adb.Sgm.requetsDTO.RegisterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        try {
            logger.info("Recebida requisição de login para o email: {}", data.email());
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User)auth.getPrincipal());
            logger.info("Usuário autenticado com sucesso: {}", data.email());
            logger.info("Usuário autenticado com sucesso: {}", token);

            return ResponseEntity.ok(new LoginResponseDTO(token));
        }catch (Exception e){
            logger.error("Erro ao fazer login para o email: {}", data.email(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer login");
        }

    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO){
        if (this.userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.email(),encryptedPassword, registerDTO.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
