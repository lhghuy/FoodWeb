package com.example.FoodHKD.controller.admin;

import com.example.FoodHKD.model.Order;
import com.example.FoodHKD.model.OrderDetail;
import com.example.FoodHKD.repository.OrderDetailRepository;
import com.example.FoodHKD.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrderAdminController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("pageTitle", "Quản lý Hóa đơn");
        return "ordermanagement";
    }

    @GetMapping("/{id}")
    public String getOrderDetail(@PathVariable("id") Integer id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/admin/orders";
        }

        return "orderdetail";
    }
}
