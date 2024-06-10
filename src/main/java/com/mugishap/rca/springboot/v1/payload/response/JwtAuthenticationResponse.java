package com.mugishap.rca.springboot.v1.payload.response;

import com.mugishap.rca.springboot.v1.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

    public JwtAuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}



