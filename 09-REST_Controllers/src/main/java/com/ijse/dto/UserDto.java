package com.ijse.dto;

import java.util.Arrays;

public class UserDto {
    int id;
    String name;
    String address;
    CityDto city;

    public UserDto(int id, String name, String address, CityDto city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                '}';
    }
}
