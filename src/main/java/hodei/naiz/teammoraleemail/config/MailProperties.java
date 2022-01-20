package hodei.naiz.teammoraleemail.config;

import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 13:41
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Component
public class MailProperties {
    @Value("${twillio.api.key}")
    private String apiKey;
    @Value("${twillio.from}")
    private String fromEmail;
    private SendGrid sendGrid;
    @Value("${twillio.templates.signup}")
    private String templateSignup;
    @Value("${client.base.url}")
    private String clientBaserUrl;

    public String getApiKey(){
        return apiKey;
    }

    public Email getFromEmail() {
        return new Email(fromEmail);
    }
    public SendGrid getSendGrid(){
        return new SendGrid(apiKey);
    }
    public String getTemplateSignup(){return templateSignup;}
    public String getClientBaserUrl(){return clientBaserUrl;}
}
