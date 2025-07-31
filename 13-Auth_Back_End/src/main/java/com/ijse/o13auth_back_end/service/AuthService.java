package com.ijse.o13auth_back_end.service;

import com.ijse.o13auth_back_end.dto.AuthDTO;
import com.ijse.o13auth_back_end.dto.AuthResponseDTO;
import com.ijse.o13auth_back_end.dto.RegisterDTO;
import com.ijse.o13auth_back_end.entity.Role;
import com.ijse.o13auth_back_end.entity.User;
import com.ijse.o13auth_back_end.repository.UserRepository;
import com.ijse.o13auth_back_end.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        User user=userRepository.findByUsername(authDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (!passwordEncoder.matches(authDTO.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("Incorrect username or password!");
        }
        String token = jwtUtil.generateToken(authDTO.getUsername());
        return new AuthResponseDTO(token);
    }

    public String register(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.valueOf(registerDTO.getRole()))
                .build();
        userRepository.save(user);
        return "User " + registerDTO.getUsername() + " registered successfully!";
    }
}
