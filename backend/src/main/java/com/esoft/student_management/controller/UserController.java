package com.esoft.student_management.controller;

import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.dto.UserRoleDTO;
import com.esoft.student_management.service.LoginService;
import com.esoft.student_management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.endpoint.api}/user")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public boolean saveUserDetails(@RequestBody UserDTO userDTO) {
        try {
            return loginService.saveUserDetails(userDTO);
        } catch (Exception e){
            logger.error("Error when saving user data : " +e);
            return  false;
        }
    }

    @PostMapping("/updateUser")
    public boolean updateUserDetails(@RequestBody UserDTO userDTO) {
        try {
            return userService.updateUserDetails(userDTO);
        } catch (Exception e){
            logger.error("Error when updating user data : " +e);
            return  false;
        }
    }

    @GetMapping("/checkByNIC/{nic}")
    public boolean checkNICExist(@PathVariable("nic") String nic ){
        try {
            return userService.checkNICExist(nic);
        } catch (Exception e){
            logger.error("Error when getting user data by nic : " +e);
            return  false;
        }
    }

    @GetMapping("/checkByUserName/{name}")
    public boolean checkUserNameExist(@PathVariable("name") String name ){
        try {
            return userService.checkUserNameExist(name);
        } catch (Exception e){
            logger.error("Error when getting user data by user name : " +e);
            return  false;
        }
    }

    @GetMapping("/getUserById/{id}")
    public UserDTO getUserDetailsById(@PathVariable("id") String id ){
        try {
            return userService.getUserDetails(id);
        } catch (Exception e){
            logger.error("Error when getting user data by user id : " +e);
            return  null;
        }
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting All User Details: ", e);
            return null;
        }
    }

    @GetMapping("/getUserRoles")
    public List<UserRoleDTO> getAllUserRoles() {
        try {
            return userService.getAllUserRoles();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getting All User Role Details: ", e);
            return null;
        }
    }
}
