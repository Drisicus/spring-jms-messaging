package es.springframework.springjmsmessaging.listener;

import es.springframework.springjmsmessaging.config.JmsConfig;
import es.springframework.springjmsmessaging.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message){
        System.out.println("Message listened");
        System.out.println(helloWorldMessage);
    }

    @JmsListener(destination = JmsConfig.REPLY_BACK_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message) throws JMSException {
        HelloWorldMessage reply = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Reply Message")
                .build();
        jmsTemplate.convertAndSend(message.getJMSReplyTo(), reply);
    }
}
