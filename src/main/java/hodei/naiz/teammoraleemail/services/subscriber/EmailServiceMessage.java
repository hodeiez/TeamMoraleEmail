package hodei.naiz.teammoraleemail.services.subscriber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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

public class EmailServiceMessage{

    @JsonProperty("to")
    private String to;
    @JsonProperty("message")
    private String message;
    @JsonProperty("username")
    private String username;

}
