package com.shop.supermarket.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {

    private int id;

    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 4,message = "password should be of min length 4")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    private String email;


    private String phoneNumber;

    private String address;

    private short enabled;



}
