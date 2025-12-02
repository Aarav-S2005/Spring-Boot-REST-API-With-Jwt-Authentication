package com.aarav.SpringBootRestApiWithJwtAuth.controller;

import com.aarav.SpringBootRestApiWithJwtAuth.DTO.InfoDTO;
import com.aarav.SpringBootRestApiWithJwtAuth.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InfoController {

    InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/")
    public ResponseEntity<InfoDTO> getInfo(){
        InfoDTO infoDTO = infoService.findInfo();
        return ResponseEntity.status(HttpStatus.OK).body(infoDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<InfoDTO> createInfo(@RequestBody InfoDTO infoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(infoService.createInfo(infoDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<InfoDTO> updateInfo(@RequestBody InfoDTO infoDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(infoService.updateInfo(infoDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<InfoDTO> deleteInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(infoService.deleteInfo());
    }
}
