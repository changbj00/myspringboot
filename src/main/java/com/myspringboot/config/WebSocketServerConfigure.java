package com.myspringboot.config;

import com.myspringboot.hander.MyStringWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 在cc.mrbird.socket目录下新建configure包，然后在该包下新建WebSocketServerConfigure配置类
 * @EnableWebSocket用于开启WebSocket相关功能，
 * 我们注入了上面创建的MyStringWebSocketHandler，并将其注册到了WebSocketHandlerRegistry
 * 当客户端通过/connecturl和服务端连接通信时，使用MyStringWebSocketHandler处理会话。
 * withSockJS的含义是，通信的客户端是通过SockJS实现的
 */
@Configuration
@EnableWebSocket
public class WebSocketServerConfigure implements WebSocketConfigurer {
    @Autowired
    private MyStringWebSocketHandler myStringWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myStringWebSocketHandler,"/connect").withSockJS();

    }
}
