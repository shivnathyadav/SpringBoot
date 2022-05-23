package com.shop.supermarket.controller;


import com.shop.supermarket.converter.ItemsConverter;
import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Items;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.service.ItemsService;
import com.shop.supermarket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersDataController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ItemsConverter itemsConverter;

    @Autowired
    private UsersConverter usersConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping("/ordersList")
    public String ordersList(Principal loginUser,Model theModel)
    {
        theModel.addAttribute("currentUser",loginUser.getName());
        theModel.addAttribute("orderedItems",itemsConverter.entityToDto(usersService.getOrdersList(loginUser.getName())));
        return "orders-list";
    }

    @GetMapping("/order")
    public String order(Model theModel)
    {
        theModel.addAttribute("items",itemsConverter.entityToDto(itemsService.getAllItemsList()));
        return "order-item";
    }

    @PostMapping("/orderItem")
    public ModelAndView orderItem(@RequestParam String itemId, Principal loggedUser)
    {
        Users user = usersService.findByUsername(loggedUser.getName());
        Items item = itemsService.getItemById(Integer.parseInt(itemId));
        user.addItem(item);
        itemsService.saveItem(item);
        return new ModelAndView("redirect:/successHandler");
    }

    @PostMapping("/deleteOrder")
    public ModelAndView deleteItem(@RequestParam int id,Principal loggedUser)
    {
        Users tempUser = usersService.findByUsername(loggedUser.getName());
        Items tempItem = itemsService.getItemById(id);
        tempUser.getItems().remove(tempItem);
        itemsService.saveItem(tempItem);
        return new ModelAndView("redirect:/user/ordersList");
    }



    @GetMapping("/updatePage")
    public String updatePage(Model model,Principal currentLoggedUser)
    {
        UsersDTO tempUser = usersConverter.entityToDto(usersService.findByUsername(currentLoggedUser.getName()));
        model.addAttribute("user",tempUser);
        return "update-page";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") UsersDTO user, BindingResult bindingResult, Model model,Principal presentUser)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("loggedUser",presentUser.getName());
            model.addAttribute("user",user);
            return "update-page";
        }
        Users tempUser = usersConverter.dtoToEntity(user);
        String encodedPassword = bCryptPasswordEncoder.encode(tempUser.getPassword());
        usersService.updateUser(presentUser.getName(),encodedPassword,tempUser.getEmail(),tempUser.getPhoneNumber(),tempUser.getAddress());
        return "redirect:/successHandler";
    }
}
