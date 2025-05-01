package br.com.fiap.TechChallenge.controllers;

import br.com.fiap.TechChallenge.dtos.ChangePasswordRequestDTO;
import br.com.fiap.TechChallenge.dtos.UserPostRequestDTO;
import br.com.fiap.TechChallenge.dtos.UserPatchRequestDTO;
import br.com.fiap.TechChallenge.entities.User;
import br.com.fiap.TechChallenge.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    )
    {
        logger.info("/users");
        var users = this.userService.findAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(
            @PathVariable("id") Long id
    )
    {
        logger.info("/users/", id);
        var user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(
           @Valid @RequestBody UserPostRequestDTO user
    )
    {
        logger.info("POST -> /users/");
        this.userService.saveUser(user);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserPatchRequestDTO user
    )
    {
        logger.info("PUT -> /users/" + id);
        this.userService.updateUser(user, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(
            @PathVariable("id") Long id,
            @Valid @RequestBody ChangePasswordRequestDTO passwordRequestDTO
    )
    {
        logger.info("PUT -> /users/" + id + "/password");
        this.userService.changePassword(passwordRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("id") Long id
    )
    {
        logger.info("DELETE -> /users/" + id);
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}