package hodei.naiz.teammoraleemail.services.subscriber;

import hodei.naiz.teammoraleemail.config.MailProperties;
import hodei.naiz.teammoraleemail.services.email.EmailSender;
import hodei.naiz.teammoraleemail.services.email.MailHelper;
import hodei.naiz.teammoraleemail.services.email.SendgridMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:58
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Service
public class SubscriberService {

    private EmailSender emailSender;
    private MailProperties mailProperties;
    private MailHelper mailHelper;
    private ReactiveRedisOperations<String, EmailServiceMessage> reactiveRedisTemplate;

    @Value("${topic.name:email-notification}")
    private String topic;

    public SubscriberService(EmailSender emailSender, MailProperties mailProperties,  ReactiveRedisOperations<String, EmailServiceMessage> reactiveRedisTemplate) {
        this.emailSender = emailSender;
        this.mailProperties = mailProperties;

        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @PostConstruct
    private void init(){
        this.reactiveRedisTemplate
                .listenTo(ChannelTopic.of(topic))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(m-> {
                    try {
                        System.out.println(emailSender.send(new SendgridMail(mailProperties,new MailHelper()).sendMail(m)) + " " + m.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}
