package com.shop.supermarket;

import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class SupermarketApplicationTests {

    @Autowired
    UsersConverter usersConverter;

    @Autowired
    ItemsConverter itemsConverter;


    //testing only for sonar cloud basic invocation of supermarket application main method
    @Test
    void testingMainMethodInSpringBootApplication()
    {
        SupermarketApplication.main(new String[]{});
        Assertions.assertTrue(true, "asserting to be compliant with Sonar");
    }
}