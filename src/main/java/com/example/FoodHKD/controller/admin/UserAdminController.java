package com.example.FoodHKD.controller.admin;

import com.example.FoodHKD.model.User;
import com.example.FoodHKD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model, Principal principal) {
        User loggedInUser = userService.getUserByUsername(principal.getName());

        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("pageTitle", "Quản lý Nhân viên");
        return "usermanagement";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        User newUser = new User();
        newUser.setCreatedAt(LocalDateTime.now());
        model.addAttribute("user", newUser);
        model.addAttribute("pageTitle", "Thêm Nhân viên mới");
        return "userform";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Sửa thông tin Nhân viên");
        return "userform";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Integer id, @ModelAttribute("user") User user) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}