package com.zsd.springboottest.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 使用注解的监听
 */
@Component
public class MyAnnotationListener {

    @PostConstruct
    public void starter(){
        System.out.println("依赖注入完成之后执行");
    }
    @EventListener
    public void listener1(MyTestEvent event) {
        System.out.println("注解监听器1:" + event.getMsg());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        System.out.println("Spring Boot应用已启动并准备就绪，开始执行初始化操作...");
        // 在这里执行需要在应用启动后进行的初始化代码
    }
}


