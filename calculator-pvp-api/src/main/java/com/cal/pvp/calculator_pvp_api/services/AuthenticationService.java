package com.cal.pvp.calculator_pvp_api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cal.pvp.calculator_pvp_api.Models.User;
import com.cal.pvp.calculator_pvp_api.Models.dtos.LoginUserDto;
import com.cal.pvp.calculator_pvp_api.Models.dtos.RegisterUserDto;
import com.cal.pvp.calculator_pvp_api.Repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        if(!this.userRepository.findByEmail(input.getEmail()).isPresent())
        {
            User user = new User(input.getEmail(), passwordEncoder.encode(input.getPassword()));
            user.setPass(passwordEncoder.encode(input.getPassword()));
            return this.userRepository.save(user);
        }

        //user must already exist with email
        return null;
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
