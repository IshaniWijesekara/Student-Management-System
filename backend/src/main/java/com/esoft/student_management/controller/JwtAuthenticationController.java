package com.esoft.student_management.controller;

import com.esoft.student_management.dto.TokenDTO;
import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.security.JwtRequest;
import com.esoft.student_management.security.JwtResponse;
import com.esoft.student_management.security.JwtTokenUtil;
import com.esoft.student_management.security.JwtUserDetailsService;
import com.esoft.student_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.endpoint.api}/login")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public TokenDTO createAuthenticationToken(@RequestParam(value ="username") String username,@RequestParam(value ="password") String password) throws Exception {
        TokenDTO tokenDTO = new TokenDTO();
        authenticate(username,password);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);
        UserDTO userType = userService.findByUserName(username);
        String type = userType.getUserType();
        tokenDTO.setToken(token);
        tokenDTO.setUserType(type);
        return tokenDTO;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
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
