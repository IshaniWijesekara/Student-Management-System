package com.esoft.student_management.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class UserDTO {
    private int userId;
    private String name;
    private String contactNo;
    private String email;
    private String userName;
    private String password;
    private String nic;
    private String address;
    private String userRoleCode;
    private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
    private String userType;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String userType) {
        this.userName = username;
        this.password = password;
        this.userType = userType;
    }

    public UserDTO(int userId, String name, String contactNo, String email, String username, String password, String nic, String address, String userRoleCode, Collection<GrantedAuthority> grantedAuthoritiesList) {
        this.userId = userId;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.userName = username;
        this.password = password;
        this.nic = nic;
        this.address = address;
        this.userRoleCode = userRoleCode;
        this.grantedAuthoritiesList = grantedAuthoritiesList;
    }
}
