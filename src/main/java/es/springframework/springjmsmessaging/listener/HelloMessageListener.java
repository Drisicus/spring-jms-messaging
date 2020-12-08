package es.springframework.springjmsmessaging.listener;

import es.springframework.springjmsmessaging.config.JmsConfig;
import es.springframework.springjmsmessaging.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers, Message message){
        System.out.println("Message listened");
        System.out.println(helloWorldMessage);
    }
}
