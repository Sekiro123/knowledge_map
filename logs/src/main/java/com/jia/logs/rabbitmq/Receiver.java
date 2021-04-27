package com.jia.logs.rabbitmq;

import com.jia.common.entity.log;
import com.google.gson.Gson;
import com.jia.logs.service.LogDAOService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(bindings = {
        @QueueBinding(
                value=@Queue(value = "logs",autoDelete = "false"),
                exchange = @Exchange(value = "amq.direct",type = "direct",autoDelete = "false"),
                key="direct-logs"
        )
})
@Component
public class Receiver {
    @Autowired
    private LogDAOService logDAOService;
    @RabbitHandler()
    public void onMessage(String meaasge){
        System.out.println("meaasge = " + meaasge);
        log log = new Gson().fromJson(meaasge, log.class);
        logDAOService.insert(log);
    }
}
