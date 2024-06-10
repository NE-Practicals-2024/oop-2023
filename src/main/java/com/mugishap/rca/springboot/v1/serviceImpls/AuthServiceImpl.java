package com.mugishap.rca.springboot.v1.serviceImpls;

import com.mugishap.rca.springboot.v1.exceptions.AppException;
import com.mugishap.rca.springboot.v1.payload.response.JwtAuthenticationResponse;
import com.mugishap.rca.springboot.v1.security.JwtTokenProvider;
import com.mugishap.rca.springboot.v1.models.User;
import com.mugishap.rca.springboot.v1.services.IAuthService;
import com.mugishap.rca.springboot.v1.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public JwtAuthenticationResponse login(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = null;
        try {
            jwt = jwtTokenProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new AppException("Error generating token", e);
        }
        User user = this.userService.getByEmail(email);
        return new JwtAuthenticationResponse(jwt, user);
    }

}
