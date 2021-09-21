package com.esoft.student_management.service;

import com.esoft.student_management.dto.LoginDTO;
import com.esoft.student_management.dto.UserDTO;

public interface LoginService {
    UserDTO checkUser(LoginDTO loginDTO);

    boolean saveUserDetails(UserDTO userDTO);

    boolean forgotPassword(LoginDTO loginDTO);
}
