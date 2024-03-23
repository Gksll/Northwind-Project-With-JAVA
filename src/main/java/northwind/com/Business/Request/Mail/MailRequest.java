package northwind.com.Business.Request.Mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    String subject;
    String text;
    String from;
    String to;
    String replyTo;
    String cc;
    String bcc;
    Date sentDate = Date.from(Instant.now());

}
