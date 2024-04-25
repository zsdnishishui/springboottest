package com.zsd.springboottest.controller;

import com.zsd.springboottest.entity.ChatMessage;
import com.zsd.springboottest.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    // 参考 https://www.jb51.net/article/255542.htm#_lab2_3_5
    @GetMapping("/hello/mono1")
    public Mono<String> mono(){
        return Mono.just("Hello Mono -  Java North");
    }

    @GetMapping("/hello/flux1")
    public Flux<String> flux(){
        return Flux.just("Hello Flux","Hello Java North");
    }
    @GetMapping(value="/history", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> getChatHistory() {
        return chatService.getChatHistory();
    }

    @PostMapping("/send")
    public void sendHistory(@RequestBody ChatMessage message) {
        System.out.println(message.getText());
        chatService.sendMessage(message);
    }
    // Other API endpoints for sending messages, joining/leaving the chat, etc.
    @GetMapping("/hello/mono")
    public Mono<String> stringMono(){
        Mono<String> from = Mono.fromSupplier(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello, Spring Reactive  date time:"+ LocalDateTime.now();
        });
        System.out.println( "thread : " + Thread.currentThread().getName()+ " ===  " + LocalDateTime.now() +"  ==========Mono function complete==========");
        return from;
    }
    @GetMapping(value = "/hello/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux1(){
        Flux<String> stringFlux = Flux.fromStream(IntStream.range(1,6).mapToObj(i ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "java north flux" + i + "date time: " +LocalDateTime.now();
        }));
        System.out.println("thread : " + Thread.currentThread().getName()+ " ===  " + LocalDateTime.now() + "  ==========Flux function complete=========");
        return stringFlux;
    }
}
