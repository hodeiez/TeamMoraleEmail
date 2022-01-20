package hodei.naiz.teammoraleemail.services.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import hodei.naiz.teammoraleemail.config.MailProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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



        public String send(Mail mail) throws IOException {
            Request req=new Request();
            req.setMethod(Method.POST);
            req.setEndpoint("mail/send");
            req.setBody(mail.build());

            Response resp= mailProperties.getSendGrid().api(req);

            return resp.getBody();
        }


    }
