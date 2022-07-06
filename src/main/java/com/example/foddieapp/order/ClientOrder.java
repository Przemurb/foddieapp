package com.example.foddieapp.order;

import com.example.foddieapp.item.Item;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
@Getter
public class ClientOrder {
    private Order order;

    public ClientOrder() {
        clear();
    }

    public void add(Item item) {
        order.getItems().add(item);
    }

    public void clear() {
        order = new Order();
        order.setStatus(OrderStatus.NEW);
    }

    public void del(int index) {
        order.getItems().remove(index);
    }

    public int size() {
        return order.getItems().size();
    }

}
