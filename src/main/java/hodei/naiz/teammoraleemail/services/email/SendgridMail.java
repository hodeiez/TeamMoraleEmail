package hodei.naiz.teammoraleemail.services.email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import hodei.naiz.teammoraleemail.config.MailProperties;
import hodei.naiz.teammoraleemail.services.subscriber.EmailServiceMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 14:02
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@AllArgsConstructor
@Data
public class SendgridMail {
    private final MailProperties mailProperties;
    private final MailHelper mailHelper;

    public Mail sendMail(EmailServiceMessage email) {
        Mail mail=new Mail();
        mail.setFrom(mailProperties.getFromEmail());

        //for signup//
        mail.setTemplateId(mailProperties.getTemplateSignup());


        mail.setSubject("this is the subject");
        mailHelper.addDynamicTemplateData("username",email.getUsername());
        mailHelper.addDynamicTemplateData("content",email.getMessage());
        mailHelper.addDynamicTemplateData("subject","welcome "+ email.getUsername());
        mailHelper.addDynamicTemplateData("confirmationUrl", mailProperties.getClientBaserUrl()+email.getConfirmationToken());
        mail.addPersonalization( mailHelper.withTos(List.of(new Email(email.getTo()))));

        return mail;

    }


}
