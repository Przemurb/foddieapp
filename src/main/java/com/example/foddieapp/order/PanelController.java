package com.example.foddieapp.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PanelController {
    private OrderRepository orderRepository;

    public PanelController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/panel/zamowienia")
    public String getOrders(@RequestParam(required = false) OrderStatus status, Model model) {
        List<Order> orders;
        if(status == null) {
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findAllByStatus(status);
        }
        model.addAttribute("orders", orders);
        return "panel/orders";
    }
}
