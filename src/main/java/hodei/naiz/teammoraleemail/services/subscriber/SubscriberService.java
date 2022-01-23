package hodei.naiz.teammoraleemail.services.subscriber;

import com.sendgrid.helpers.mail.Mail;
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

import static java.lang.String.valueOf;

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

    public SubscriberService(EmailSender emailSender, MailProperties mailProperties, ReactiveRedisOperations<String, EmailServiceMessage> reactiveRedisTemplate) {
        this.emailSender = emailSender;
        this.mailProperties = mailProperties;
        this.mailHelper = new MailHelper();

        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    @PostConstruct
    private void init(){
        this.reactiveRedisTemplate
                .listenTo(ChannelTopic.of(topic))
                .map(ReactiveSubscription.Message::getMessage)
                .subscribe(m-> {
                    try {
                        System.out.println(m.toString());
                        System.out.println(actionSelector(m.getEmailType(),m));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    private String actionSelector(EmailType type,EmailServiceMessage m) throws IOException {
        switch(type){
            case SIGNUP-> {emailSender.send(SendgridMail.forSignedUp().mailProperties(mailProperties).mailHelper(mailHelper).email(m).build().getMail()).subscribe(); return "Sign up mail sent";}
            case ADDED_TO_TEAM -> {emailSender.send(SendgridMail.forAddedToTeam().mailProperties(mailProperties).mailHelper(mailHelper).email(m).build().getMail()).subscribe(); return "Added to team sent";}
            case FORGOT_PASS -> {emailSender.send(SendgridMail.forForgotPass().mailProperties(mailProperties).mailHelper(mailHelper).email(m).build().getMail()).subscribe(); return "Sent to forgot pass";}

            default -> {
                return "failed";
            }
        }
    }

}
