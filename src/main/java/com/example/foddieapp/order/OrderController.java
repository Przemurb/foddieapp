package com.example.foddieapp.order;

import com.example.foddieapp.home.Message;
import com.example.foddieapp.item.Item;
import com.example.foddieapp.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class OrderController {
    private ClientOrder clientOrder;
    private ItemRepository repository;

    public OrderController(ClientOrder clientOrder, ItemRepository repository) {
        this.clientOrder = clientOrder;
        this.repository = repository;
    }

    @GetMapping("/zamowienie/dodaj")
    public String addItemToOrder (@RequestParam Long itemId, Model model) {
        Optional<Item> foundItem = repository.findById(itemId);
        foundItem.ifPresent(clientOrder::add);
        if(foundItem.isPresent()) {
            model.addAttribute("message", new Message("Dodano", "Do zamówienia dodano: " +
                    foundItem.get().getName()));
        } else {
            model.addAttribute("message", new Message("Nic nie dodano", "Z menu pobrano błędne id " +
                    itemId));
        }
        return "message";
    }
}
