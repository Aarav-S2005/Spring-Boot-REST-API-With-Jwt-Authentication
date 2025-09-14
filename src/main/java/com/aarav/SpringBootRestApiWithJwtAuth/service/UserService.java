package com.aarav.SpringBootRestApiWithJwtAuth.service;

import com.aarav.SpringBootRestApiWithJwtAuth.DTO.LoginResponseDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.DTO.UserLoginDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.DTO.UserRegisterDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.exceptions.GlobalException;
import com.aarav.SpringBootRestApiWithJwtAuth.mapping.UserMapping;
import com.aarav.SpringBootRestApiWithJwtAuth.model.User;
import com.aarav.SpringBootRestApiWithJwtAuth.repository.UserRepository;
import com.aarav.SpringBootRestApiWithJwtAuth.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapping userMapping, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO registerUser(UserRegisterDTO userRegisterDTO) {

        if(userRepository.existsUserByUsername(userRegisterDTO.getUsername())){
            throw new GlobalException(HttpStatus.CONFLICT, "Username already exists");
        }

        userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        User user = UserMapping.toDTO(userRegisterDTO);
        userRepository.save(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        String token = jwtTokenProvider.generateJwtToken(auth);
        return new LoginResponseDTO(user.getUsername(), token);
    }

    public LoginResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        if(!userRepository.existsUserByUsername(userLoginDTO.getUsername())){
            throw new GlobalException(HttpStatus.NOT_FOUND, "Username not found");
        }
        System.out.println("authenticating");
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),
                        userLoginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtTokenProvider.generateJwtToken(auth);
        return new LoginResponseDTO(userLoginDTO.getUsername(), token);
    }

}
