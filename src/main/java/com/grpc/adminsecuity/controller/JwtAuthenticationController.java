package com.grpc.adminsecuity.controller;

import com.grpc.adminsecuity.config.JwtTokenUtil;
import com.grpc.adminsecuity.model.AdminDto;
import com.grpc.adminsecuity.model.JwtRequest;
import com.grpc.adminsecuity.model.JwtResponse;
import com.grpc.adminsecuity.service.JwtAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtAdminDetailsService jwtAdminDetailsService;


    @PostMapping("/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getAdminname(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtAdminDetailsService.loadUserByUsername(authenticationRequest.getAdminname());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody AdminDto admin) throws Exception {
        return ResponseEntity.ok(jwtAdminDetailsService.save(admin));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
