package com.esoft.student_management.controller;

import com.esoft.student_management.dto.LoginDTO;
import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.endpoint.api}/login")
public class LoginController {
    final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/saveUser")
    public boolean saveUserDetails(@RequestBody UserDTO userDTO) {
        try {
            return loginService.saveUserDetails(userDTO);
        } catch (Exception e){
            logger.error("Error when saving user data : " +e);
            return  false;
        }
    }

    @PostMapping("/forgotPassword")
    public boolean forgotPassword(@RequestBody LoginDTO loginDTO) {
        try {
            return loginService.forgotPassword(loginDTO);
        } catch (Exception e){
            logger.error("Error when updating password : " +e);
            return  false;
        }
    }


    @PostMapping("/checkUser")
    public UserDTO checkUserDetails(@RequestBody LoginDTO loginDTO) {
        try {
            return loginService.checkUser(loginDTO);
        }catch (Exception e){
            logger.error("Error when checking user data : " +e);
            return  null;
        }
    }
}
