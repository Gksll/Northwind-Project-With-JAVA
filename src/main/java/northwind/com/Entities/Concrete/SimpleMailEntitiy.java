package northwind.com.Entities.Concrete;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import northwind.com.Business.Request.Mail.MailRequest;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emails")
public class SimpleMailEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    private String sender_email;

    @Size(max = 100)
    private String replyTo;

    @Size(max = 50)
    private String recipient_email;

    @Size(max = 50)
    private String cc;

    @Size(max = 50)
    private String bcc;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;

    @Size(max = 255)
    private String subject;
    @Size(max = 10000)
    private String text;
    @Size(max = 10000)
    private String attachments;

    public SimpleMailEntitiy(MailRequest mailRequest) {
        this.sender_email = mailRequest.getFrom();
        this.replyTo = mailRequest.getReplyTo();
        this.recipient_email = mailRequest.getTo() != null ? mailRequest.getTo() : null;
        this.cc = mailRequest.getCc();
        this.bcc = mailRequest.getBcc();
        this.sentDate = mailRequest.getSentDate();
        this.subject = mailRequest.getSubject();
        this.text = mailRequest.getText();
    }
}
