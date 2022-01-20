package hodei.naiz.teammoraleemail.services.subscriber;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 10:57
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@Data
@AllArgsConstructor
public class EmailServiceMessage {

    private String to;
    private String message;
    private String username;

}
