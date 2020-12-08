package es.springframework.springjmsmessaging.sender;

import es.springframework.springjmsmessaging.config.JmsConfig;
import es.springframework.springjmsmessaging.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMesasge(){
        System.out.println("Sending Message...");
        HelloWorldMessage message = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
        System.out.println("...Message Sent");
    }
}
