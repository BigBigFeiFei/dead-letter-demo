package com.mfrank.rabbitmq.deadletter.demo.controller;

import com.mfrank.rabbitmq.deadletter.demo.mq.BusinessMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("rabbitmq")
@RestController
public class RabbitMQMsgController {

    @Autowired
    private BusinessMessageSender sender;

    /*@RequestMapping("sendmsg")
    public void sendMsg(String msg){
        sender.sendMsg(msg);
    }
    @RequestMapping("sendmsg1")
    public void sendMsg1(String msg,Integer delayTime){
        sender.sendDelayMsg(msg,delayTime);
    }*/
    @RequestMapping("sendmsg1")
    public void sendMsg1(String msg,Integer delayTime){
        sender.sendDelayMsg(msg,delayTime);
    }
    @RequestMapping("sendmsg2")
    public void sendMsg2(String msg,Integer type){
        sender.sendMsg3(msg,type);
    }
    @RequestMapping("sendmsg3")
    public void sendMsg3(String msg,Integer delayTime){
        sender.sendDelayMsgD(msg,delayTime);
    }
}
