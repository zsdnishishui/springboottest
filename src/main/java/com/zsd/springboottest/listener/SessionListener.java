package com.zsd.springboottest.listener;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements HttpSessionListener {
    @PostConstruct
    public void starter(){
        System.out.println("SessionListener依赖注入完成之后执行");
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session创建时的逻辑
        System.out.println("Session created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session销毁时的逻辑
        System.out.println("Session destroyed: " + se.getSession().getId());
    }
}
