//package com.jia.user.rabbitMq;
//
//import com.jia.user.dao.UserDAO;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Receiver {
//    @Autowired
//    private UserDAO userDAO;
//    @RabbitListener(bindings = {
//            @QueueBinding(
//                    value = @Queue,
//                    key={"user.#"},
//                    exchange = @Exchange(type = "topic",name = "submitLabel")
//            )
//    })
//    public void receve1(String message){
//        System.out.println("receive function start!");
//        System.out.println("message = " + message);
//        userDAO.incUser(message);
//    }
//}
