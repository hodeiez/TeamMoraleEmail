package hodei.naiz.teammoraleemail.services.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import hodei.naiz.teammoraleemail.config.MailProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 13:55
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */

    @Service
    @AllArgsConstructor
    public class EmailSender {

        private final MailProperties mailProperties;
private final WebClient webClient;

        public Flux<String> send(Mail mail) {

            try {
               return webClient.mutate().baseUrl(mailProperties.getSendgridBaserUrl()).build().post().uri("mail/send")
                       .body(BodyInserters.fromValue(mail.build()))
                       .header("Authorization","Bearer "+ mailProperties.getApiKey())
                       .header("Content-Type","application/json")
                       .retrieve().onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new RuntimeException("Error on send"))).bodyToFlux(String.class);
            } catch (IOException e) {
                e.printStackTrace();
                return Flux.error(new RuntimeException(e.toString()));
            }
        }


    }
