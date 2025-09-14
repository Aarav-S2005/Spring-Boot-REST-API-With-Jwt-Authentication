package com.aarav.SpringBootRestApiWithJwtAuth.service;

import com.aarav.SpringBootRestApiWithJwtAuth.DTO.InfoDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.exceptions.GlobalException;
import com.aarav.SpringBootRestApiWithJwtAuth.mapping.InfoMapping;
import com.aarav.SpringBootRestApiWithJwtAuth.model.Info;
import com.aarav.SpringBootRestApiWithJwtAuth.model.User;
import com.aarav.SpringBootRestApiWithJwtAuth.repository.InfoRepository;
import com.aarav.SpringBootRestApiWithJwtAuth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    InfoRepository infoRepository;
    UserRepository userRepository;

    public InfoService(InfoRepository infoRepository,  UserRepository userRepository) {
        this.infoRepository = infoRepository;
        this.userRepository = userRepository;
    }

    private Authentication getAuthentication(){
        return (Authentication)SecurityContextHolder.getContext().getAuthentication();
    }

    public InfoDTO findInfo(){
        Authentication auth  = getAuthentication();
        String username = auth.getName();
        Info info =  infoRepository.findInfoByUsername(username);
        if(info == null){
            return new InfoDTO();
        }
        return InfoMapping.toDTO(info);
    }

    public InfoDTO createInfo(InfoDTO infoDTO){
        Authentication auth  = getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "User not found");
        }

        if(infoRepository.existsByEmail(infoDTO.getEmail())){
            throw new GlobalException(HttpStatus.CONFLICT, "Email not unique");
        }

        Info info = user.getInfo();
        if (info != null) {
            throw new GlobalException(HttpStatus.CONFLICT, "Info already exists");
        }

        Info newInfo = InfoMapping.toEntity(infoDTO);
        newInfo.setUser(user);
        user.setInfo(newInfo);

        userRepository.save(user);

        return infoDTO;
    }

    public InfoDTO updateInfo(InfoDTO infoDTO){
        Authentication auth  = getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "User not found");
        }
        Info info = user.getInfo();
        if(info == null){
            throw new GlobalException(HttpStatus.NOT_FOUND, "Info not found");
        }

        Info newInfo = InfoMapping.toEntity(infoDTO);
        newInfo.setUser(user);
        user.setInfo(newInfo);
        userRepository.save(user);
        return infoDTO;
    }

    public InfoDTO deleteInfo(){
        Authentication auth  = getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "User not found");
        }
        Info info = user.getInfo();
        user.setInfo(null);
        userRepository.save(user);
        return InfoMapping.toDTO(info);
    }
}
