package com.aarav.SpringBootRestApiWithJwtAuth.mapping;

import com.aarav.SpringBootRestApiWithJwtAuth.DTO.InfoDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.model.Info;

public class InfoMapping {
    public static InfoDTO toDTO(Info info) {
        if (info == null) return null;

        InfoDTO dto = new InfoDTO();
        dto.setName(info.getName());
        dto.setAge(info.getAge());
        dto.setEmail(info.getEmail());
        return dto;
    }

    public static Info toEntity(InfoDTO dto) {
        if (dto == null) return null;

        Info info = new Info();
        info.setName(dto.getName());
        info.setAge(dto.getAge());
        info.setEmail(dto.getEmail());
        return info;
    }
}
