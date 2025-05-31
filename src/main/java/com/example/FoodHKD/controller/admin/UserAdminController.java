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
        model.addAttribute("pageTitle", "Thêm Nhân viên mới");
        return "userform";
    }


    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("pageTitle", "Sửa thông tin Nhân viên");
        return "userform";
    }


}