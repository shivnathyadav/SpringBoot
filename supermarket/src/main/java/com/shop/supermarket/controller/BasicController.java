package com.shop.supermarket.controller;


import com.shop.supermarket.converter.UsersConverter;
import com.shop.supermarket.dto.RolesDTO;
import com.shop.supermarket.dto.UsersDTO;
import com.shop.supermarket.entity.Roles;
import com.shop.supermarket.entity.Users;
import com.shop.supermarket.service.RolesService;
import com.shop.supermarket.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;

@Controller
public class BasicController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UsersConverter usersConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loginPage")
    public String login()
    {
        return "login-page";
    }

    @GetMapping("/successHandler")
    public String successHandler(Principal loggedUser, Model theModel)
    {
        theModel.addAttribute("loggedUser",loggedUser.getName());
        return "home";
    }

    @GetMapping("/access-denied")
    public String failHandler()
    {
        return "denied";
    }

    @GetMapping("/registerPage")
    public String registerPage()
    {
        return "register";
    }

    @PostMapping("/saveNewUser")
    public String registerPage(@Valid @ModelAttribute("user") UsersDTO user, Model model)
    {
        if (usersService.findByUsername(user.getUsername())!=null)
        {
            return "duplicate-user";
        }
        user.setEnabled((short) 1);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersService.saveUser(usersConverter.dtoToEntity(user));
        model.addAttribute("finalUser",user);
        return "redirect:/loginPage?user=True";
    }

    @GetMapping("/role")
    public String role()
    {
        return "role-page";
    }

    @PostMapping("/saveRole")
    public String saveRole(@RequestParam("authority") RolesDTO role, @RequestParam("username") String username, Model model, Principal loggedUser)
    {
        if(!usersService.findByUsername(username).getRoles().isEmpty())
        {
            return "prompt-page";
        }
        Users tempUser = usersService.findByUsername(username);
        Roles tempRole = rolesService.getRolesById(role.getAuthority());
        tempUser.addRole(tempRole);
        tempRole.addUser(tempUser);
        usersService.saveUser(tempUser);
        return "redirect:/loginPage";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Principal loggedUser)
    {
        usersService.deleteUser(usersService.findByUsername(loggedUser.getName()));
        return "redirect:/loginPage";
    }

}
