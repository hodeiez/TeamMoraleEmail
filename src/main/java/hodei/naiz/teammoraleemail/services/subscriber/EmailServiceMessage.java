package hodei.naiz.teammoraleemail.services.subscriber;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:57
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Data
public class EmailServiceMessage {
    private String to;
    private String message;
    private String username;
    private String emailType;
    private String confirmationToken;

    @Builder(builderMethodName = "buildSignedUp")
    public static EmailServiceMessage signedUp(String to, String username, String emailType, String confirmationToken, String message) {
        EmailServiceMessage emailService = new EmailServiceMessage();
        emailService.to = to;
        emailService.username = username;
        emailService.emailType = emailType;
        emailService.confirmationToken = confirmationToken;
        emailService.message = message;
        return emailService;

    }
}
