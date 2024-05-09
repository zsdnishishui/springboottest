package com.zsd.springboottest.listener;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class MyTestEvent extends ApplicationEvent {

    private String msg;

    public MyTestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }
}

