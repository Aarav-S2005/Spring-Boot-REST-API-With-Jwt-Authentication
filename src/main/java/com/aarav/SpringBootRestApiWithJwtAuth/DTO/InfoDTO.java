package com.aarav.SpringBootRestApiWithJwtAuth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {

    private String name;
    private Integer age;
    private String email;
}
