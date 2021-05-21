package com.jia.user.rabbitMq;
//
//import com.jia.user.dao.UserDAO;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
import com.jia.user.dao.UserDAO;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @Autowired
    private UserDAO userDAO;
    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue(value = "incUserTagNum",autoDelete = "false"),
                    exchange = @Exchange(value = "amq.direct",type = "direct",autoDelete = "false"),
                    key="incUserTagNum"
            )
    })
    public void receve1(String message){
//        System.out.println("receive function start!");
        System.out.println( message +"'s TagNum + 1");
        userDAO.incNum_tag(message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue(value = "incNumArticles",autoDelete = "false"),
                    exchange = @Exchange(value = "amq.direct",type = "direct",autoDelete = "false"),
                    key="incNumArticles"
            )
    })
    public void receve2(String message){
//        System.out.println("receive function start!");
        System.out.println( message +"'s numArticles + 1");
//        userDAO.incNum_tag(message);
        userDAO.incNum_articles(message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value=@Queue(value = "incNumSentences",autoDelete = "false"),
                    exchange = @Exchange(value = "amq.direct",type = "direct",autoDelete = "false"),
                    key="incNumSentences"
            )
    })
    public void receve3(String message){
//        System.out.println("receive function start!");
        System.out.println( message +"'s numSentences + 1");
//        userDAO.incNum_tag(message);
        userDAO.incNum_sentences(message);
    }
}
