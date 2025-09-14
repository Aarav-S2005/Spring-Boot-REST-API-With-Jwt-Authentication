package com.aarav.SpringBootRestApiWithJwtAuth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserRegisterDTO {

    private String username;
    private String password;
}
