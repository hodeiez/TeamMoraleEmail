package hodei.naiz.teammoraleemail.services.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.*;

/**
 * Created by Hodei Eceiza
 * Date: 1/20/2022
 * Time: 14:33
 * Project: TeamMoraleEmail
 * Copyright: MIT
 */
@With
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailHelper extends Personalization {
    @JsonProperty("to")
    private List<Email> tos;

    @JsonProperty("cc")
    private List<Email> ccs;

    @JsonProperty("bcc")
    private List<Email> bccs;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("headers")
    private Map<String, String> headers;


    @JsonProperty("custom_args")
    private Map<String, String> customArgs;

    @JsonProperty("dynamic_template_data")
    private Map<String, Object> dynamicTemplateData;

    @JsonProperty("to")
    public List<Email> getTos() {
        if (tos == null) {
            return Collections.<Email>emptyList();
        }
        return tos;
    }

    public void addTo(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (tos == null) {
            tos = new ArrayList<Email>();
            tos.add(newEmail);
        } else {
            tos.add(newEmail);
        }
    }

    @JsonProperty("cc")
    public List<Email> getCcs() {
        if (ccs == null) {
            return Collections.<Email>emptyList();
        }
        return ccs;
    }

    public void addCc(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (ccs == null) {
            ccs = new ArrayList<Email>();
            ccs.add(newEmail);
        } else {
            ccs.add(newEmail);
        }
    }


    @JsonProperty("bcc")
    public List<Email> getBccs() {
        if (bccs == null) {
            return Collections.<Email>emptyList();
        }
        return bccs;
    }

    public void addBcc(Email email) {
        Email newEmail = new Email();
        newEmail.setName(email.getName());
        newEmail.setEmail(email.getEmail());
        if (bccs == null) {
            bccs = new ArrayList<Email>();
            bccs.add(newEmail);
        } else {
            bccs.add(newEmail);
        }
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("headers")
    public Map<String, String> getHeaders() {
        if (headers == null) {
            return Collections.<String, String>emptyMap();
        }
        return headers;
    }

    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
            headers.put(key, value);
        } else {
            headers.put(key, value);
        }
    }


    @JsonProperty("custom_args")
    public Map<String, String> getCustomArgs() {
        if (customArgs == null) {
            return Collections.<String, String>emptyMap();
        }
        return customArgs;
    }

    public void addCustomArg(String key, String value) {
        if (customArgs == null) {
            customArgs = new HashMap<String, String>();
            customArgs.put(key, value);
        } else {
            customArgs.put(key, value);
        }
    }


    @JsonProperty("dynamic_template_data")
    public Map<String, Object> getDynamicTemplateData() {
        return dynamicTemplateData == null
                ? Collections.<String, Object>emptyMap() : dynamicTemplateData;
    }

    public void addDynamicTemplateData(String key, Object value) {
        if (dynamicTemplateData == null) {
            dynamicTemplateData = new HashMap<String, Object>();
        }
        dynamicTemplateData.put(key, value);
    }
}
