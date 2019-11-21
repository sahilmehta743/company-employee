package com.crud.companyemployee.controller;

import com.crud.companyemployee.model.AuthenticationRequest;
import com.crud.companyemployee.model.AuthenticationResponse;
import com.crud.companyemployee.service.MyUserDetailsService;
import com.crud.companyemployee.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthenticateController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public AuthenticateController(AuthenticationManager authenticationManager,
                                  JwtUtil jwtTokenUtil, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        log.info("Inside class -> AuthenticateController method -> createAuthenticationToken()");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Password!!!", e);
        }
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        log.info("Outside class -> AuthenticateController method -> createAuthenticationToken()");
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
