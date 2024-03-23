package northwind.com.Business.Abstracts;

import jakarta.mail.MessagingException;
import northwind.com.Business.Request.Mail.MailRequest;
import northwind.com.Core.Results.Result;

import java.io.IOException;

public interface MailService {
    Result sendMail(MailRequest request);

    Result sendMimeMail(MailRequest request) throws IOException, MessagingException;

}
