package hodei.naiz.teammoraleemail.services.subscriber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:58
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Service
public class SubscriberService {


    private ReactiveRedisOperations<String, EmailServiceMessage> reactiveRedisTemplate;

    @Value("${topic.name:email-notification}")
    private String topic;

    public SubscriberService(ReactiveRedisOperations<String, EmailServiceMessage> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @PostConstruct
    private void init(){
        this.reactiveRedisTemplate
                .listenTo(ChannelTopic.of(topic))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(System.out::println);
    }

}
