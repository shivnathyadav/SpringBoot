package com.shop.supermarket;

import com.shop.supermarket.controller.UsersDataController;
import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserDataControllerTest {

    UsersDataController usersDataController;

    @Autowired
    ItemsConverter itemsConverter;

    @Autowired
    UsersConverter usersConverter;

    @MockBean
    UsersService usersService;

    @BeforeEach
    void setup()
    {
        usersDataController = new UsersDataController();
    }

    @Test
    void contextLoads()
    {
        assertThat(usersDataController).isNotNull();
    }

}
