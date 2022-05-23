package com.shop.supermarket.service;

import com.shop.supermarket.dto.RolesDTO;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.repository.RolesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class RolesServiceImplTest {

    @MockBean
    RolesRepository rolesRepository;

    @Autowired
    RolesService rolesService;

    @Test
    void getRolesById() {
        Roles role = new Roles("ROLE_STAFF");
        when(rolesRepository.getById("ROLE_STAFF")).thenReturn(role);
        assertEquals(rolesService.getRolesById("ROLE_STAFF"),role);
    }

    @Test
    void saveRole() {
        Roles role = new Roles("ROLE_STAFF");
        rolesService.saveRole(role);
        verify(rolesRepository,times(1)).save(role);
    }

    @Test
    void testingRolesDTO()
    {
        RolesDTO role1 = new RolesDTO();
        RolesDTO role2 = new RolesDTO("ROLE_STAFF");
        role1.setAuthority("ROLE_USER");
        assertEquals("ROLE_STAFF",role2.getAuthority());
    }
}