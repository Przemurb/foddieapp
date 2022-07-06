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
    private final ClientOrder clientOrder;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public OrderController(ClientOrder clientOrder, ItemRepository itemRepository, OrderRepository orderRepository) {
        this.clientOrder = clientOrder;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/zamowienie/dodaj")
    public String addItemToOrder (@RequestParam Long itemId, Model model) {
        Optional<Item> foundItem = itemRepository.findById(itemId);
        foundItem.ifPresent(clientOrder::add);
        if(foundItem.isPresent()) {
            model.addAttribute("message", new Message("Dodano do zamówienia", "Do zamówienia dodano: " +
                    foundItem.get().getName()));
        } else {
            model.addAttribute("message", new Message("Nic nie dodano", "Z menu pobrano błędne id " +
                    itemId));
        }
        return "message";
    }

    @GetMapping("/zamowienie")
    public String getCurrentOrder(Model model) {
        model.addAttribute("order", clientOrder.getOrder());
        model.addAttribute("sum", clientOrder
                .getOrder()
                .getItems()
                .stream()
                .mapToDouble(Item::getPrice)
                .sum());

        return "order";
    }
}
