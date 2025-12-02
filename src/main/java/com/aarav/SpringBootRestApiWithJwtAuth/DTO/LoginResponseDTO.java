package com.aarav.SpringBootRestApiWithJwtAuth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDTO {
    
    private String username;
    private String token;
}
