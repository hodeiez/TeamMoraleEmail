package hodei.naiz.teammoraleemail.services.email;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import hodei.naiz.teammoraleemail.config.MailProperties;
import hodei.naiz.teammoraleemail.services.subscriber.EmailServiceMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 14:02
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */

@Data
public class SendgridMail {
    private MailProperties mailProperties;
    private MailHelper mailHelper;
    private Mail mail;

    @Builder(builderMethodName = "forSignedUp",builderClassName = "ForSignedUp")
    public static SendgridMail signedUp(EmailServiceMessage email,MailProperties mailProperties,MailHelper mailHelper) {
        SendgridMail sendgridMail=new SendgridMail();
        sendgridMail.mail=new Mail();
        sendgridMail.mail.setFrom(mailProperties.getFromEmail());

        sendgridMail.mail.setTemplateId(mailProperties.getTemplateSignup());
        mailHelper.addDynamicTemplateData("username",email.getUsername());
        mailHelper.addDynamicTemplateData("content",email.getMessage());
        mailHelper.addDynamicTemplateData("subject","welcome "+ email.getUsername());
        mailHelper.addDynamicTemplateData("confirmationUrl", mailProperties.getClientBaserUrl()+"/confirmAccount/token="+email.getConfirmationToken());
        sendgridMail.mail.addPersonalization( mailHelper.withTos(List.of(new Email(email.getTo()))));

        return sendgridMail;

    }
    @Builder(builderMethodName = "forAddedToTeam")
    public static SendgridMail addedToTeam(EmailServiceMessage email,MailProperties mailProperties,MailHelper mailHelper) {
        SendgridMail sendgridMail=new SendgridMail();
        sendgridMail.mail=new Mail();
        sendgridMail.mail.setFrom(mailProperties.getFromEmail());

        sendgridMail.mail.setTemplateId(mailProperties.getTemplateAddedToTeam());
        mailHelper.addDynamicTemplateData("username",email.getUsername());
        mailHelper.addDynamicTemplateData("content",email.getMessage());
        mailHelper.addDynamicTemplateData("subject","welcome "+ email.getUsername() + " to " + email.getTeamName());
        mailHelper.addDynamicTemplateData("web_link", mailProperties.getClientBaserUrl());
        mailHelper.addDynamicTemplateData("team_name",email.getTeamName());
        sendgridMail.mail.addPersonalization( mailHelper.withTos(List.of(new Email(email.getTo()))));

        return sendgridMail;

    }
    @Builder(builderMethodName = "forForgotPass",builderClassName = "ForForgotPass")
    public static SendgridMail forgotPass(EmailServiceMessage email,MailProperties mailProperties,MailHelper mailHelper) {
        SendgridMail sendgridMail=new SendgridMail();
        sendgridMail.mail=new Mail();
        sendgridMail.mail.setFrom(mailProperties.getFromEmail());

        sendgridMail.mail.setTemplateId(mailProperties.getTemplateForgotPass());
        mailHelper.addDynamicTemplateData("username",email.getUsername());
        mailHelper.addDynamicTemplateData("content",email.getMessage());
        mailHelper.addDynamicTemplateData("subject","Hi "+ email.getUsername() + " looks like you forgot your password");
        mailHelper.addDynamicTemplateData("web_link",  mailProperties.getClientBaserUrl()+"forgotPass?token="+email.getConfirmationToken());


        sendgridMail.mail.addPersonalization( mailHelper.withTos(List.of(new Email(email.getTo()))));

        return sendgridMail;

    }


}
