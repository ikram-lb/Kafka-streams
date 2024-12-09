package org.sid.kafka_streams.web;

import jakarta.validation.Path;
import jdk.jfr.Event;
import org.sid.kafka_streams.Entities.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {

    @Autowired
    private StreamBridge streamBridge;
//    send event to an topic kafka
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String topic,@PathVariable String name){
        PageEvent Event1=new PageEvent(name,Math.random()>0.5?"U1":"u2",new Date(),new Random().nextInt(9000));
        streamBridge.send(topic, Event1);
     return Event1;
    }


}
