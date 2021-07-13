package com.mfrank.rabbitmq.deadletter.demo.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_EXCHANGE_NAME;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_QUEUEA_ROUTING_KEY;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_QUEUEB_ROUTING_KEY;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_QUEUEC_ROUTING_KEY;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_QUEUED_ROUTING_KEY;

@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /* public void sendMsg(String msg){
         rabbitTemplate.convertSendAndReceive(BUSINESS_EXCHANGE_NAME, "", msg);
     }
     public void sendDelayMsg(String msg, Integer delayTime) {
         rabbitTemplate.convertAndSend(BUSINESS_EXCHANGE_NAME, "", msg, a ->{
             a.getMessageProperties().setDelay(delayTime);
             return a;
         });
     }*/
    public void sendMsg3(String msg, int type) {
        switch (type) {
            case 1:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case 2:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
        }
    }
    public void sendDelayMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUEC_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            a.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return a;
        });
    }

    public void sendDelayMsgD(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUED_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            //a.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return a;
        });
    }
}
