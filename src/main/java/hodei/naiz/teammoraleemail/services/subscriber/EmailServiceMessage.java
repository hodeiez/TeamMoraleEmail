package hodei.naiz.teammoraleemail.services.subscriber;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:57
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailServiceMessage {
    private String to;
    private String message;
    private String username;
    private EmailType emailType;
    private String confirmationToken;
    private String teamName;
    @Builder(builderMethodName = "buildSignedUp")
    public static EmailServiceMessage signedUp(String to, String username, EmailType emailType, String confirmationToken, String message) {
        EmailServiceMessage emailService = new EmailServiceMessage();
        emailService.to = to;
        emailService.username = username;
        emailService.emailType = emailType;
        emailService.confirmationToken = confirmationToken;
        emailService.message = message;
        return emailService;

    }
    @Builder(builderMethodName = "buildAddedToTeam")
    public static EmailServiceMessage addedToTeam(String to, String username, EmailType emailType, String message,String teamName) {
        EmailServiceMessage emailService = new EmailServiceMessage();
        emailService.to = to;
        emailService.username = username;
        emailService.emailType = emailType;
        emailService.teamName = teamName;
        emailService.message = message;
        return emailService;

    }
    @Builder(builderMethodName = "buildForgotPass",builderClassName = "BuildForgotPass")
    public static EmailServiceMessage forgotPass(String to, String username, EmailType emailType, String message,String confirmationToken) {
        EmailServiceMessage emailService = new EmailServiceMessage();
        emailService.to = to;
        emailService.username = username;
        emailService.emailType = emailType;
        emailService.message = message;
        return emailService;

    }
}
