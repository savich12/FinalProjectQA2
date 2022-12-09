package finalProject.tests;

import finalProject.utilities.JavaMailUtility;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

class SendMailTest {
    @Test
    public void sendMail() throws MessagingException {
        JavaMailUtility javaMailUtility = new JavaMailUtility();
        javaMailUtility.sendMail();
    }
}
