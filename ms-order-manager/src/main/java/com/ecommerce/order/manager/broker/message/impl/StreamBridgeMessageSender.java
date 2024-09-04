package com.ecommerce.order.manager.broker.message.impl;

import com.ecommerce.order.manager.broker.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamBridgeMessageSender implements MessageSender {

    private final StreamBridge streamBridge;

    @Override
    public void send(String bindingName, byte[] message) {
        streamBridge.send(bindingName, message);
    }
}