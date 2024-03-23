package northwind.com.Business.Concretes;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import northwind.com.Business.Abstracts.MailService;
import northwind.com.Business.Request.Mail.MailRequest;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessResult;
import northwind.com.Core.mapping.ModelMapperService;
import northwind.com.DataAccess.MailRepository;
import northwind.com.Entities.Concrete.SimpleMailEntitiy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailManager implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ModelMapperService modelMapperService;
    @Autowired
    private MailRepository mailRepository;


    @Override
    public Result sendMail(MailRequest request) {
        SimpleMailMessage message = mailRequestToSimpleMailMessage(request);
        mailSender.send(message);
        SimpleMailEntitiy simpleMailEntitiyToSave = new SimpleMailEntitiy(request);
        this.mailRepository.save(simpleMailEntitiyToSave);
        return new SuccessResult("Mail sent succeccfully!");
    }


    @Override
    public Result sendMimeMail(MailRequest request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setFrom(request.getFrom());
        message.setTo(request.getTo());
        message.setText(request.getText());
        message.setSubject(request.getSubject());
        message.setBcc(request.getBcc());
        message.setCc(request.getCc());
        message.setReplyTo(request.getReplyTo());
        message.setSentDate(request.getSentDate());
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\goksel\\Pictures\\Camera Roll\\picture.jpg"));
        message.addAttachment("picture.jpg", file);
        mailSender.send(mimeMessage);
        SimpleMailEntitiy simpleMailEntitiyToSave = new SimpleMailEntitiy(request);
        simpleMailEntitiyToSave.setAttachments(file.getPath());
        this.mailRepository.save(simpleMailEntitiyToSave);
        return new SuccessResult();
    }

    private SimpleMailMessage mailRequestToSimpleMailMessage(MailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(request.getFrom());
        message.setTo(request.getTo());
        message.setText(request.getText());
        message.setSubject(request.getSubject());
        message.setBcc(request.getBcc());
        message.setCc(request.getCc());
        message.setReplyTo(request.getReplyTo());
        message.setSentDate(request.getSentDate());
        return message;
    }
}
