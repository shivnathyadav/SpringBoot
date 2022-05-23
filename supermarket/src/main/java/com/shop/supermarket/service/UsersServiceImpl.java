package com.shop.supermarket.service;

import com.shop.supermarket.entity.Users;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository theUsersRepository)
    {
        usersRepository =theUsersRepository;
    }

    //create in CRUD
    @Override
    public void saveUser(Users theUser) {
        usersRepository.save(theUser);
    }

    //read in CRUD
    @Override
    public List<Items> getOrdersList(String username) {
        return usersRepository.getOrdersList(username);
    }

    //read in CRUD
    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    //update in CRUD
    @Override
    public void updateUser(String username, String password, String email, String phoneNumber, String address) {
        usersRepository.updateData(username,password,email,phoneNumber,address);
    }
    //delete in CRUD
    @Override
    public void deleteUser(Users user) {
        usersRepository.delete(user);
    }


}
