package com.example.foddieapp.controllers;

import com.example.foddieapp.item.Item;
import com.example.foddieapp.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ItemRepository repository;

    public HomeController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    String home(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "index";
    }
}
