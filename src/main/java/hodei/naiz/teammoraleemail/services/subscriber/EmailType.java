package hodei.naiz.teammoraleemail.services.subscriber;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 12:44
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
public enum EmailType {
    SIGNUP("Signed up"),FORGOT_PASS("forgotten password"),ADDED_TO_TEAM("added to team");
    private final String value;
    EmailType(String valueName) {
        value=valueName;
    }

    @Override
    public String toString() {
        return  value;
    }
}
