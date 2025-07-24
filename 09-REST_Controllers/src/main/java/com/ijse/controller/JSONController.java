package com.ijse.controller;

import com.ijse.dto.CityDto;
import com.ijse.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("json")
public class JSONController {
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String save(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return "saved";
    }

    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("John");
        userDto.setAddress("Colombo");
        userDto.setCity(new CityDto("Moratuwa", "10400"));
        return userDto;
    }
}
