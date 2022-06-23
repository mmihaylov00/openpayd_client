package com.example.openpayd_client.controller;

import com.example.openpayd_client.dto.external.RegisterUserDataRequestDTO;
import com.example.openpayd_client.dto.response.ErrorResponseDTO;
import com.example.openpayd_client.exception.HttpResponseException;
import com.example.openpayd_client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterUserDataRequestDTO individual) {
        try {
            this.userService.registerAccount(individual);
        } catch (HttpResponseException e) {
            return e.getResponse();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<?> balance(Principal userData) {
        try {
            return ResponseEntity.ok(this.userService.balance(userData.getName()));
        } catch (HttpResponseException e) {
            return e.getResponse();
        }
    }
}
