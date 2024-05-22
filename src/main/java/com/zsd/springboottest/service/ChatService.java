package com.zsd.springboottest.service;

import com.zsd.springboottest.entity.ChatMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class ChatService {
    private final FluxSink<ChatMessage> chatMessageSink;
    private final Flux<ChatMessage> chatMessageFlux;

    public ChatService() {
        DirectProcessor<ChatMessage> processor = DirectProcessor.create();
        this.chatMessageSink = processor.sink();
        this.chatMessageFlux = processor.onBackpressureBuffer().publish().autoConnect();
    }

    public Flux<ChatMessage> getChatHistory(String username) {
        return chatMessageFlux.filter(chatMessage -> chatMessage.getSender().equals(username));
    }

    public void sendMessage(ChatMessage message) {
        chatMessageSink.next(message);
    } // Other methods to handle joining/leaving the chat, user authentication, etc.
}
