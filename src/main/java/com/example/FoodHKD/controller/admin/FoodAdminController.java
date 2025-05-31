package com.example.FoodHKD.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // For form binding
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; // For submitting forms
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.FoodHKD.model.FoodItem;
import com.example.FoodHKD.service.CategoryService; // Assuming you have a CategoryService
import com.example.FoodHKD.service.FoodItemService;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/foods")
public class FoodAdminController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        return "productmanagement";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "productform";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        return "productform";
    }

}