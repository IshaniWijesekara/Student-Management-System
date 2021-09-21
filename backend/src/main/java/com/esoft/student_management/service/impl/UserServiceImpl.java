package com.esoft.student_management.service.impl;

import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.dto.UserRoleDTO;
import com.esoft.student_management.entity.User;
import com.esoft.student_management.entity.UserRole;
import com.esoft.student_management.repository.UserRepository;
import com.esoft.student_management.repository.UserRoleRepository;
import com.esoft.student_management.repository.util.UserJDBCRepository;
import com.esoft.student_management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJDBCRepository userJDBCRepository;


    @Override
    public boolean updateUserDetails(UserDTO userDTO) {
        if (userDTO != null) {
            User byUserName = userRepository.findByUsername(userDTO.getUserName());
            if(byUserName != null) {
                byUserName.setPassword(userDTO.getPassword());
                byUserName.setUserRole(userRoleRepository.findByName(userDTO.getUserRoleCode()));
                byUserName.setUsername(userDTO.getUserName());
                byUserName.setNic(userDTO.getNic());
                byUserName.setAddress(userDTO.getAddress());
                byUserName.setName(userDTO.getName());
                byUserName.setEmail(userDTO.getEmail());
                byUserName.setContactNo(userDTO.getContactNo());
                byUserName.setCreatedDate(new Date());

                userRepository.save(byUserName);
                return true;
            }
        }else {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        User byUserName = userRepository.findByUsername(userName);
        if (byUserName != null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean checkNICExist(String nic) {
        User byNic = userRepository.findByNic(nic);
        if (byNic != null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        List<User> all = userRepository.findAll();

        for (User user:all) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserType(user.getUsername());
            userDTO.setNic(user.getNic());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setContactNo(user.getContactNo());
            userDTO.setUserRoleCode(user.getUserRole().getName());
            userDTO.setUserId(user.getId());
            userDTO.setAddress(user.getAddress());
            list.add(userDTO);
        }
        return list;
    }

    @Override
    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> all = userRoleRepository.findAll();
        List<UserRoleDTO>list = new ArrayList<>();

        for (UserRole userRole:all) {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setId(userRole.getCode());
            userRoleDTO.setName(userRole.getName());
            list.add(userRoleDTO);
        }
        return list;
    }

    @Override
    public UserDTO getUserDetails(String id) {
        User user = userRepository.findById(Integer.valueOf(id)).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(user.getAddress());
        userDTO.setUserId(user.getId());
        userDTO.setUserRoleCode(user.getUserRole().getName());
        userDTO.setContactNo(user.getContactNo());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setNic(user.getNic());
        userDTO.setUserName(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    @Override
    public UserDTO findByUserName(String userName) {


    }

}
