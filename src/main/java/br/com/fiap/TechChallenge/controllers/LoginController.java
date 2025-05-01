package br.com.fiap.TechChallenge.controllers;

import br.com.fiap.TechChallenge.dtos.LoginRequestDTO;
import br.com.fiap.TechChallenge.services.LoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequestDTO request
    )
    {
        logger.info("POST -> /login");
        boolean isAuthenticated = this.loginService.authenticate(request.login(), request.password());

        return isAuthenticated ?
                ResponseEntity.ok("Login efetuado com sucesso!") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos!");
    }
}