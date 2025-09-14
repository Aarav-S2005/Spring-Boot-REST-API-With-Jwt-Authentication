package com.aarav.SpringBootRestApiWithJwtAuth.mapping;

import com.aarav.SpringBootRestApiWithJwtAuth.DTO.UserLoginDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.DTO.UserRegisterDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    public static User toEntity(UserLoginDTO userLoginDTO) {
        User user = new User();
        user.setUsername(userLoginDTO.getUsername());
        user.setPassword(userLoginDTO.getPassword());
        return user;
    }

    public static User toDTO(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());
        return user;
    }

}
