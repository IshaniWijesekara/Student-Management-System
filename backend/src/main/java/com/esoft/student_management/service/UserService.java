package com.esoft.student_management.service;

import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.dto.UserRoleDTO;

import java.util.List;

public interface UserService {
    boolean updateUserDetails(UserDTO userDTO);

    boolean checkUserNameExist(String userName);

    boolean checkNICExist(String nic);

    List<UserDTO> getAllUsers();

    List<UserRoleDTO> getAllUserRoles();



    UserDTO findByUserName(String userName);

    UserDTO getUserDetails(String id);
}
