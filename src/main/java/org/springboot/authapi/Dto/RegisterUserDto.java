package org.springboot.authapi.Dto;
import lombok.Data;


@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }
}

//--------------------------------------------8---------------------------------