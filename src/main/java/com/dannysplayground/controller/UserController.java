package com.dannysplayground.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dannysplayground.dao.UserManagerImpl;
import com.dannysplayground.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource(name="userService")
    private UserManagerImpl userService ;

    // Displaying the initial users list.
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getPersons(Model model) {
        LOGGER.debug("Request to fetch all users from the postgress database");
        List user_list = userService.getList();
        model.addAttribute("users", user_list);     
        return "welcome";
    }

    // Opening the add new user form page.
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        LOGGER.debug("Request to open the new user form page");
        model.addAttribute("userAttr", new User());
        return "form";
    }

    // Opening the edit user form page.
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam(value="id", required=true) String id, Model model) {
        LOGGER.debug("Request to open the edit user form page");   
        model.addAttribute("userAttr", userService.getUser(Integer.parseInt(id)));     
        return "form";
    }

    // Deleting the specified user.
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) String id, Model model) {
        userService.deleteUser(Integer.parseInt(id));
        return "redirect:list";
    }

    // Adding a new user or updating an existing user.
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("userAttr") User user) {
        userService.saveUser(user);
        return "redirect:list";
    }
}
