package com.felipe.estoque.controller;

import com.felipe.estoque.domain.User;
import com.felipe.estoque.dto.RegisterRequestDTO;
import com.felipe.estoque.dto.ResponseDTO;
import com.felipe.estoque.dto.loginRequestDTO;
import com.felipe.estoque.infra.security.TokenService;
import com.felipe.estoque.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody loginRequestDTO dto){
        User user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(dto.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity registe(@RequestBody RegisterRequestDTO dto){
        Optional<User> user = userRepository.findByEmail(dto.email());
        if (user.isEmpty()){
            User newUser = new User();
            newUser.setEmail(dto.email());
            newUser.setSenha(dto.password());
            newUser.setNome(dto.name());

            userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
