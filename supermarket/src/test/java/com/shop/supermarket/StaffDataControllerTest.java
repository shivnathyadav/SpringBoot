package com.shop.supermarket;


import com.shop.supermarket.controller.StaffDataController;
import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.dto.ItemsDTO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.repository.ItemsRepository;
import com.shop.supermarket.service.ItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
class StaffDataControllerTest {
    private MockMvc mockMvc;

    StaffDataController staffDataController;

    @Autowired
    ItemsConverter itemsConverter;

    @MockBean
    ItemsRepository itemsRepository;

    @Mock
    ItemsService itemsService;

    @BeforeEach
    void setup()
    {
        staffDataController = new StaffDataController();
        staffDataController.setItemsServiceObject(itemsService);
        staffDataController.setItemsConverterObject(itemsConverter);
        mockMvc= MockMvcBuilders.standaloneSetup(staffDataController).build();
    }

    @Test
    void contextLoads()
    {
        assertThat(staffDataController).isNotNull();
    }


    @Test
    void showFormForAddItem() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/addItem"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("item-form"));
    }

    @Test
    void stockListTesting() throws Exception {
        List<Items> itemsList = Arrays.asList(
                new Items(1, "blue lays",5,"lays india"),
                new Items(2, "green lays",5,"lays india")
        );
        when(itemsRepository.findAll()).thenReturn(itemsList);

        mockMvc.perform(MockMvcRequestBuilders.get("/staff/stockList"))
                .andExpect((MockMvcResultMatchers.model()).attribute("allItems",itemsService.getAllItemsList()))
                .andExpect(MockMvcResultMatchers.view().name("stock-list"));
    }

    @Test
    void deleteItemTesting() throws Exception {
        itemsService.deleteItemById(1);
        mockMvc.perform(MockMvcRequestBuilders.post("/staff/deleteItem").param("id","1"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/staff/stockList"));
    }


}