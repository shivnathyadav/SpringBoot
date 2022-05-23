package com.shop.supermarket.converter;

import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

    @Component
    public class UsersConverter {

        @Autowired
        private ModelMapper modelMapper;

        public UsersDTO entityToDto(Users users)
        {
            return modelMapper.map(users,UsersDTO.class);
        }

        public List<UsersDTO> entityToDto(List<Users> users)
        {
            return users.stream().map(this::entityToDto).collect(Collectors.toList());
        }

        public Users dtoToEntity(UsersDTO usersDTO)
        {
            return modelMapper.map(usersDTO,Users.class);
        }

    }

