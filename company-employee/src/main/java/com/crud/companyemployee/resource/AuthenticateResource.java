package com.crud.companyemployee.resource;

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

import static com.crud.companyemployee.constant.AppConstant.API_ROOT_URL;

@Slf4j
@RestController
@RequestMapping(API_ROOT_URL)
public class AuthenticateResource {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public AuthenticateResource(AuthenticationManager authenticationManager,
                                JwtUtil jwtTokenUtil, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
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
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
