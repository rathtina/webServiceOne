package org.springboot.authapi.ResponseEnities;
import lombok.Data;


@Data
public class LoginResponse {
    private String token;

    private long expiresIn;


    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;  // Allows method chaining
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;  // Allows method chaining
    }



}

//--------------------------------------------------------9---------------------------------------------
