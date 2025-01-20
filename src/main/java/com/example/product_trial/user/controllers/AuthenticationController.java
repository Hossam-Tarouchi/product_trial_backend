package com.example.product_trial.user.controllers;

import com.example.product_trial.user.models.LoginResponse;
import com.example.product_trial.user.models.LoginUser;
import com.example.product_trial.user.models.RegisterUser;
import com.example.product_trial.user.models.UserEntity;
import com.example.product_trial.user.services.AuthenticationService;
import com.example.product_trial.user.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/account")
    public ResponseEntity<UserEntity> register(@RequestBody RegisterUser registerUser) {
        UserEntity registeredUser = authenticationService.signup(registerUser);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/token")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUser loginUserDto) {

        UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(loginResponse);
    }


}
