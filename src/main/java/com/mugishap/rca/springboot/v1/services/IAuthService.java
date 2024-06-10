package com.mugishap.rca.springboot.v1.services;

import com.mugishap.rca.springboot.v1.payload.response.JwtAuthenticationResponse;

public interface IAuthService {
    JwtAuthenticationResponse login(String email, String password);

}
