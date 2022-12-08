package finalProject.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MailCredentials {
    String email = "mailLogin/mailFrom";
    String password = "mailPassword";
    String recipient = "mailTo";
}
