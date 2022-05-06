package com.example.foddieapp.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ItemController {
    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/danie/{name}")
    String item (@PathVariable String name, Model model) {
        Optional<Item> item = repository.findByNameIgnoreCase(name.replaceAll("-", " "));
        item.ifPresent(value -> model.addAttribute("item", value));
        return item.map(value ->"item").orElse("redirect:/");
    }
}
