package com.shop.supermarket.service;

import com.shop.supermarket.entity.Roles;

public interface RolesService {
    //read in CRUD
    public Roles getRolesById(String id);
    //create in CRUD
    public void saveRole(Roles role);
}
