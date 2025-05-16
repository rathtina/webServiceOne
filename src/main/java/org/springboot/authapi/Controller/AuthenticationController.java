package org.springboot.authapi.Controller;

import org.springboot.authapi.Dto.LoginUserDto;
import org.springboot.authapi.Dto.RegisterUserDto;
import org.springboot.authapi.Enities.User;
import org.springboot.authapi.ResponseEnities.LoginResponse;
import org.springboot.authapi.Service.AuthenticationService;
import org.springboot.authapi.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registerUser=authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticateUser = authenticationService.authenticate(loginUserDto);

        String jwtToken=jwtService.generateToken(authenticateUser);
        LoginResponse loginResponse= new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}


//--------------------------------------------------------12----------------------------------------------