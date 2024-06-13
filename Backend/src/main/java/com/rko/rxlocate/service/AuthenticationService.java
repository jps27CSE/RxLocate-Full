package com.rko.rxlocate.service;

import com.rko.rxlocate.domain.User;
import com.rko.rxlocate.dto.AuthenticationRequestDTO;
import com.rko.rxlocate.dto.RegisterRequestDTO;
import com.rko.rxlocate.domain.enums.Role;
import com.rko.rxlocate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequestDTO request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
    }

    public String authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                request.getPassword()));
        try {
            var user = repository.findByUsername(request.getUsername()).orElseThrow();
            return jwtService.generateToken(user);
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return usernameNotFoundException.getMessage();
        }
    }
}