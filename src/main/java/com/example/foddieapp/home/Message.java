package com.example.foddieapp.home;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String title;
    private String description;

    public Message(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
