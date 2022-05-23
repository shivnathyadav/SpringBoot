package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;

import java.util.List;

public interface ItemsService {

    public void saveItem(Items items);

    public List<Items> getAllItemsList();

    public Items getItemById(int id);

    public void deleteItemById(int itemId);

}
