package org.springboot.authapi.Dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    private String password;



    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}

//---------------------------------------7--------------------------------