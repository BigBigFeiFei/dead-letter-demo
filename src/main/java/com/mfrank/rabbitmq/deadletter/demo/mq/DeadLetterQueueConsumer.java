package com.mfrank.rabbitmq.deadletter.demo.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DEAD_LETTER_QUEUEA_NAME;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DEAD_LETTER_QUEUEB_NAME;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DEAD_LETTER_QUEUEC_NAME;
import static com.mfrank.rabbitmq.deadletter.demo.config.RabbitMQConfig1.DELAY_QUEUED_NAME;

@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列A收到消息：{}", new Date().toString(), msg);
        //手动签收
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列B收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
    @RabbitListener(queues = DEAD_LETTER_QUEUEC_NAME)
    public void receiveC(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列C收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
    @RabbitListener(queues = DELAY_QUEUED_NAME)
    public void receiveD(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},延迟对队列D收到消息：{}", new Date().toString(), msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}