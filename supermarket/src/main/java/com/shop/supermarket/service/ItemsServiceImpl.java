package com.shop.supermarket.service;

import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService{

    ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository theItemsRepository)
    {
        itemsRepository=theItemsRepository;
    }

    //create in CRUD
    //update in CRUD
    @Override
    public void saveItem(Items items) {
        itemsRepository.save(items);
    }

    //read in CRUD
    @Override
    public List<Items> getAllItemsList() {
        return itemsRepository.findAll();
    }

    //read in CRUD
    @Override
    public Items getItemById(int id) {
        return itemsRepository.getById(id);
    }

    //delete in CRUD
    @Override
    public void deleteItemById(int itemId) {
        itemsRepository.deleteById(itemId);
    }



}
