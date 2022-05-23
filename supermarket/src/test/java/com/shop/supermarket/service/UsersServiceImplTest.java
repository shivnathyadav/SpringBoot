package com.shop.supermarket.service;

import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.repository.RolesRepository;
import com.shop.supermarket.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersServiceImplTest {

    @Autowired
    UsersService usersService;

    @MockBean
    UsersRepository usersRepository;

    @Autowired
    RolesService rolesService;

    @MockBean
    RolesRepository rolesRepository;

    @Autowired
    UsersConverter usersConverter;

    @Test
    void saveUser() {
        Users user =  new Users("akhil","akhil123","akhil@gmail.com","9701209751","delhi",(short)1);
        when(usersRepository.save(user)).thenReturn(user);
        usersService.saveUser(user);
        verify(usersRepository,times(1)).save(user);
    }

    @Test
    void getOrdersList() {
        List<Items> ordersList = new ArrayList<>();
        Items item1 = new Items(1,"coke",5,"coca cola");
        Items item2 = new Items(2,"kurkure",10,"haldirams");
        ordersList.add(item1);
        ordersList.add(item2);
        when(usersRepository.getOrdersList("john")).thenReturn(ordersList);
        assertEquals(2,usersService.getOrdersList("john").size());
    }

    @Test
    void findByUsername() {
        Users user = new Users("john","john123","john@gmail.com","9701209751","hyderabad",(short)1);
        when(usersRepository.findByUsername("john")).thenReturn(user);
        assertEquals(user,usersService.findByUsername("john"));
    }

    @Test
    void checkingUsersDTO()
    {
        UsersDTO finalUserDTO = new UsersDTO();
        UsersDTO usersDTO = new UsersDTO(1,"john","john123","john@gmail.com","9701209751","hyderabad",(short)1);
        finalUserDTO.setUsername(usersDTO.getUsername());
        finalUserDTO.setUsername("mary");
        finalUserDTO.setPassword("mary123");
        finalUserDTO.setEmail(usersDTO.getEmail());
        finalUserDTO.setPhoneNumber(usersDTO.getPhoneNumber());
        finalUserDTO.setEnabled(usersDTO.getEnabled());
        finalUserDTO.setAddress(usersDTO.getAddress());
        assertEquals("mary123",finalUserDTO.getPassword());
    }


    @Test
    void updateUser() {
        Users user = new Users("nikhil","nikhil123","nikhil@gmail.com","9701209751","delhi",(short)1);
        when(usersRepository.findByUsername("nikhil")).thenReturn(user);
        Users modifyingUser = usersService.findByUsername("nikhil");
        modifyingUser.setPhoneNumber("9701853281");
        usersService.updateUser(modifyingUser.getUsername(),modifyingUser.getPassword(),modifyingUser.getEmail(),modifyingUser.getPhoneNumber(),modifyingUser.getAddress());
        assertEquals(user,usersRepository.findByUsername("nikhil"));
    }

    @Test
    void deleteUser() {
        Users user =  new Users("akhil","akhil123","akhil@gmail.com","9701209751","delhi",(short)1);
        usersService.deleteUser(user);
        verify(usersRepository,times(1)).delete(user);
    }


    @Test
    void saveRoleByAddingToUser() {
        Users user = new Users("sham","sham123","sham@gmail.com","9701209751","delhi",(short)1);
        when(usersRepository.findByUsername("sham")).thenReturn(user);
        Users modifyingUser = usersService.findByUsername("sham");
        modifyingUser.setRoles(Stream.of(new Roles("ROLE_STAFF")).collect(Collectors.toList()));
        usersService.saveUser(modifyingUser);
        assertEquals(user,usersRepository.findByUsername("sham"));
    }

    @Test
    void saveRoleByAddingToRole() {
        Users user = new Users("sham","sham123","sham@gmail.com","9701209751","delhi",(short)1);
        when(usersRepository.findByUsername("sham")).thenReturn(user);
        Roles role1 = new Roles("ROLE_STAFF");
        Roles role = new Roles();
        role.setAuthority(role1.getAuthority());
        role.addUser(null);
        role.addUser(user);
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(role);
        user.setRoles(rolesList);
        rolesService.saveRole(role);
        assertEquals(user,usersRepository.findByUsername("sham"));
        System.out.println(usersRepository.findByUsername("sham").getRoles().get(0).getAuthority());
    }

    @Test
    void saveRoleByAddingUserToRole() {
        Users user = new Users("sham","sham123","sham@gmail.com","9701209751","delhi",(short)1);
        Roles role = new Roles("ROLE_STAFF");
        role.addUser(user);
        user.addRole(role);
        when(usersRepository.findByUsername("sham")).thenReturn(user);
        usersService.saveUser(user);
        assertEquals(user,usersRepository.findByUsername("sham"));
        System.out.println(usersRepository.findByUsername("sham").getRoles().get(0));
    }

    @Test
    void testingUserConverterEntityToDTO()
    {
        Users user1 = new Users("akhil","akhil123","akhil@gmail.com","9701209751","hyderabad",(short)1);
        Users user2 = new Users("nikhil","nikhil123","nikhil@gmail.com","9701209751","hyderabad",(short)1);
        UsersDTO usersDTO1 = usersConverter.entityToDto(user1);
        List<UsersDTO> usersDTOList = usersConverter.entityToDto(Stream.of(user1,user2).collect(Collectors.toList()));
        Users user3 = usersConverter.dtoToEntity(usersDTO1);
        assertEquals(2,usersDTOList.size());
        assertEquals("akhil@gmail.com",usersDTO1.getEmail());
        assertEquals("akhil@gmail.com",user3.getEmail());
    }
}