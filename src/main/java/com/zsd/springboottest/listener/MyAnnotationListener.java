package com.zsd.springboottest.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用注解的监听
 */
@Component
public class MyAnnotationListener {

    @EventListener
    public void listener1(MyTestEvent event) {
        System.out.println("注解监听器1:" + event.getMsg());
    }
}


