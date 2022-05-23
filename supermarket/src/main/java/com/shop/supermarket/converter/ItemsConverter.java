package com.shop.supermarket.converter;

import com.shop.supermarket.dto.ItemsDTO;
import com.shop.supermarket.entity.Items;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemsConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ItemsDTO entityToDto(Items items)
    {
        return modelMapper.map(items,ItemsDTO.class);
    }

    public List<ItemsDTO> entityToDto(List<Items> items)
    {
        return items.stream().map(this::entityToDto).collect(Collectors.toList());
    }


    public Items dtoToEntity(ItemsDTO itemsDTO)
    {
        return modelMapper.map(itemsDTO,Items.class);
    }

}
