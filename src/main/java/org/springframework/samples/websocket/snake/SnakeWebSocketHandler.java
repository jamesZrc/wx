package org.springframework.samples.websocket.snake;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class SnakeWebSocketHandler extends TextWebSocketHandler {

    private static final AtomicInteger snakeIds = new AtomicInteger(0);

    private static final AtomicInteger count = new AtomicInteger(0);

    private final int id;
    private Snake snake;

    public SnakeWebSocketHandler() {
        this.id = snakeIds.getAndIncrement();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        count.set(count.get() + 1);
        System.out.println("连接：" + count);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println(payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        count.set(count.get() - 1);
        System.out.println("失败：" + count);
    }
}
