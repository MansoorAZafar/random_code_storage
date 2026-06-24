package com.cal.pvp.calculator_pvp_api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cal.pvp.calculator_pvp_api.Models.LoginResponse;
import com.cal.pvp.calculator_pvp_api.Models.User;
import com.cal.pvp.calculator_pvp_api.Models.dtos.LoginUserDto;
import com.cal.pvp.calculator_pvp_api.Models.dtos.RegisterUserDto;
import com.cal.pvp.calculator_pvp_api.Models.dtos.UserDto;
import com.cal.pvp.calculator_pvp_api.services.AuthenticationService;
import com.cal.pvp.calculator_pvp_api.services.JwtService;


@RestController
public class LoginController {   
    
    private final JwtService jwtService;    
    private final AuthenticationService authenticationService;

    public LoginController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto request) throws Exception {
        User authenticatedUser = authenticationService.authenticate(request);
        
        String jwtToken = jwtService.generateToken(authenticatedUser);
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
    
        UserDto.setEmail(request.getEmail());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto entity) {
        User registeredUser = authenticationService.signup(entity);
        UserDto.setEmail(entity.getEmail());

        return registeredUser != null 
            ? ResponseEntity.ok(registeredUser) 
            : ResponseEntity.badRequest().body(registeredUser);
    }
    

    @GetMapping("/welcome")
    public String getMethodName() {
        return "Hello! You are authenticated to get here!";
    }
    

    
}
