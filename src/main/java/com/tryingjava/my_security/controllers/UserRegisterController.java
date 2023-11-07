package com.tryingjava.my_security.controllers;

import com.tryingjava.my_security.DTO.UserDTO;
import com.tryingjava.my_security.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register/")
public class UserRegisterController {
    private UserService userService;

    public UserRegisterController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/account")
    public String getUserAccount(Model model){
        model.addAttribute("user", new UserDTO());
        return"/account";
    }

    @PostMapping("/account")
    public String postUserAccount(@ModelAttribute("user") UserDTO userDTO, RedirectAttributes redirectAttributes){
        userService.saveUser(userDTO);
        redirectAttributes.addAttribute("success", true);
        return "redirect:/register/account";
    }

}
