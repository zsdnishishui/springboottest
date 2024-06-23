package com.zsd.springboottest.controller;

import com.zsd.springboottest.listener.MyTestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zsd.service.ServiceHello;

@RestController
@RequestMapping("/base")
public class HelloController {

    @Autowired
    private ServiceHello serviceHello;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/")
    public String hello() {

        return serviceHello.hello();
    }



    @RequestMapping(value = "/test/testPublishEvent1")
    public void testPublishEvent() {
        // 默认是同步调用
        // 如果改成异步参考：https://blog.csdn.net/yubaojin/article/details/115569767
        applicationEventPublisher.publishEvent(new MyTestEvent(this, "我来了！"));
    }
}
