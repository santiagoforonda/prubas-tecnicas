package com.santyman.hermes.securityField.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santyman.hermes.dtos.userDTO.UserDto;
import com.santyman.hermes.exceptions.customExceptions.UserNotFoundException;
import com.santyman.hermes.securityField.config.UserInfoService;
import com.santyman.hermes.securityField.jwt.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*")
public class AuthController {
    
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userInfoService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if(auth.isAuthenticated()){
            AuthResponse token = new AuthResponse(jwtService.getToken(loginRequest));
            return ResponseEntity.ok(token);
        }else{
            throw new UserNotFoundException(loginRequest.getEmail());
        }
    }
}