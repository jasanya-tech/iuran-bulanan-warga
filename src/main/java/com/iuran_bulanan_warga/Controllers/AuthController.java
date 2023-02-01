package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.LoginRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Requests.UserRequest;
import com.iuran_bulanan_warga.Services.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Auth", description = "Authentication and authorization")

public class AuthController {
  private final AuthService authService;

  @Operation(summary = "Register", description = "Endpoint for register and save the user data in database")
  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest)  {
    return authService.serviceRegister(userRequest);
  }

  @Operation(summary = "Login", description = "Endpoint for login process")
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest)  {
    return authService.serviceLogin(loginRequest);
  }
}
