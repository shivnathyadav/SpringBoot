package com.shop.supermarket.service;

import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public Roles getRolesById(String id) {
        return rolesRepository.getById(id);
    }

    @Override
    public void saveRole(Roles role) {
        rolesRepository.save(role);
    }
}
