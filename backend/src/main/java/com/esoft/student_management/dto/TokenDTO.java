package com.esoft.student_management.dto;

public class TokenDTO {

    private String token;
    private String userType;

    public TokenDTO() {
    }

    public TokenDTO(String token, String userType) {
        this.token = token;
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
