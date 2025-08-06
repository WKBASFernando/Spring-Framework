package com.ijse.o13auth_back_end.controller;

import com.ijse.o13auth_back_end.dto.ApiResponse;
import com.ijse.o13auth_back_end.dto.AuthDTO;
import com.ijse.o13auth_back_end.dto.RegisterDTO;
import com.ijse.o13auth_back_end.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "User Registered Successfully",
                        authService.register(registerDTO)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(
                new ApiResponse(
                        200,
                        "OK",
                        authService.authenticate(authDTO)
                )
        );
    }
}
