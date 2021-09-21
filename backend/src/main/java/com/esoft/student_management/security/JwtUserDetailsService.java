package com.esoft.student_management.security;

import com.esoft.student_management.dto.UserDTO;
import com.esoft.student_management.entity.User;
import com.esoft.student_management.entity.UserRole;
import com.esoft.student_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);

        if (!s.equals(null)) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
    }


    public User save(UserDTO user) {
        User newUser = new User();
        UserRole userRole = new UserRole();
        userRole.setName(user.getName());
        userRole.setCreatedDate(new Date());
        newUser.setUsername(user.getUserName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
