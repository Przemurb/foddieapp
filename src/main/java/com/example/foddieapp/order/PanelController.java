package com.example.foddieapp.order;

import com.example.foddieapp.item.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/panel/zamowienie/{id}")
    public String order(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> orderPanel(o, model))
                .orElse("redirect:/panel/zamowienia");
    }

    private String orderPanel(Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("sum", order.getItems().stream().mapToDouble(Item::getPrice).sum());
        return "panel/order";
    }
}
