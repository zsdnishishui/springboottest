package com.zsd.springboottest.controller;

import com.zsd.springboottest.entity.User;
import com.zsd.springboottest.listener.MyTestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import org.zsd.service.ServiceHello;

@RestController
@RequestMapping("/base")
@CrossOrigin
public class HelloController {

    @Autowired
    private ServiceHello serviceHello;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/")
    public String hello() {

        return serviceHello.hello();
    }

    /**
     * 接收textarea中的换行符
     * @param user
     * @return
     */
    @PostMapping("/hello")
    public String getHello(User user) {
        String str = user.getMessage();
        String[] i = str.split("\r\n");
        System.out.println(str);
        return str;
    }

    @RequestMapping(value = "/test/testPublishEvent1", method = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})
    public void testPublishEvent() {
        // 默认是同步调用
        // 如果改成异步参考：https://blog.csdn.net/yubaojin/article/details/115569767
        applicationEventPublisher.publishEvent(new MyTestEvent(this, "我来了！"));
    }
}
