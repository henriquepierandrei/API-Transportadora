package com.Monitoramento.API_Transportadora.controllers;



import com.Monitoramento.API_Transportadora.infra.security.TokenService;
import com.Monitoramento.API_Transportadora.models.UserModel;
import com.Monitoramento.API_Transportadora.services.AuthService;
import com.Monitoramento.API_Transportadora.services.CPFValidatorService;
import com.Monitoramento.API_Transportadora.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService getUsername;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final CPFValidatorService cpfValidatorService;
    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto body) {
        Optional<UserModel> user = getUsername.getUserByEmail(body.email());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("Email or Password Incorrect!");
        }

        if (passwordEncoder.matches(body.password(), user.get().getPassword())) {
            String token = this.tokenService.generateToken(user.get());
            return ResponseEntity.ok(new ResponseDto(token, user.get().getEmail(), user.get().getName(), user.get().getLastName()));
        }
        return ResponseEntity.badRequest().body("Email or Password Incorrect!");
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDto body) {
        Optional<UserModel> user = getUsername.getUserByEmail(body.email());

        if (user.isEmpty()) {
            UserModel newUser = new UserModel();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setLastName(body.lastName());
            if (!this.cpfValidatorService.isValid(body.cpf())){return ResponseEntity.badRequest().body("CPF Invalid!");}
            newUser.setCpf(body.cpf());

            this.getUsername.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            if (this.authService.registerIsValid(body.cpf(), body.phone())){
                return ResponseEntity.ok(new ResponseRegisterDto(newUser.getName(), newUser.getLastName(), newUser.getCpf(), newUser.getEmail(), token));
            }
        }

        return ResponseEntity.badRequest().build();
    }
}