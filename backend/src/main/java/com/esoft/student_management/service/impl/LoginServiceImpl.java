package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.LoginDTO;
import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.entity.User;
import com.esoft.student_management.entity.UserRole;
import com.esoft.student_management.repository.UserRepository;
import com.esoft.student_management.repository.UserRoleRepository;
import com.esoft.student_management.security.JwtRequestFilter;
import com.esoft.student_management.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDTO checkUser(LoginDTO loginDTO) {
        if (loginDTO != null) {
            User byUserNameAndPassword = userRepository.findByUsernameAndPassword(loginDTO.getUserName(),
                    loginDTO.getPassword());
            if (byUserNameAndPassword != null) {
                UserDTO userDTO = new UserDTO();
                userDTO.setContactNo(byUserNameAndPassword.getContactNo());
                userDTO.setEmail(byUserNameAndPassword.getEmail());
                userDTO.setName(byUserNameAndPassword.getName());
                userDTO.setNic(byUserNameAndPassword.getNic());
                userDTO.setUserId(byUserNameAndPassword.getId());
                userDTO.setUserName(byUserNameAndPassword.getUsername());
                userDTO.setUserRoleCode(byUserNameAndPassword.getUserRole().getName());
                return userDTO;
            }else {
               return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public boolean saveUserDetails(UserDTO userDTO) {
        if (userDTO != null) {
            User user = new User();
            UserRole byCode = userRoleRepository.findByName(userDTO.getUserRoleCode());
            user.setUsername(userDTO.getUserName());
            user.setContactNo(userDTO.getContactNo());
            user.setEmail(userDTO.getEmail());
            user.setName(userDTO.getName());
            user.setNic(userDTO.getNic());
            user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
            user.setUserRole(byCode);
            user.setAddress(userDTO.getAddress());
            userRepository.save(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean forgotPassword(LoginDTO loginDTO) {
        if (loginDTO != null) {
            User byUserName = userRepository.findByUsername(loginDTO.getUserName());
            byUserName.setPassword(loginDTO.getPassword());
            userRepository.save(byUserName);
            return true;
        }else {
            return false;
        }
    }
}
