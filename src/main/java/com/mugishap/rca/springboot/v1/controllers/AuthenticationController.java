package com.mugishap.rca.springboot.v1.controllers;

import com.mugishap.rca.springboot.v1.payload.request.LoginDTO;
import com.mugishap.rca.springboot.v1.payload.response.ApiResponse;
import com.mugishap.rca.springboot.v1.services.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(ApiResponse.success("Login successful", this.authService.login(dto.getEmail(), dto.getPassword())));
    }
}