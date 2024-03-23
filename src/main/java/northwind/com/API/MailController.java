package northwind.com.API;

import jakarta.mail.MessagingException;
import northwind.com.Business.Abstracts.CategoryService;
import northwind.com.Business.Abstracts.MailService;
import northwind.com.Business.Concretes.CategoryManager;
import northwind.com.Business.Request.Category.CreateCategoryRequest;
import northwind.com.Business.Request.Category.DeleteCategoryRequest;
import northwind.com.Business.Request.Category.UpdateCategoryRequest;
import northwind.com.Business.Request.Mail.MailRequest;
import northwind.com.Business.Response.Category.GetAllCategoryResponse;
import northwind.com.Business.Response.Category.GetCategoryResponse;
import northwind.com.Core.Results.DataResult;
import northwind.com.Core.Results.Result;
import northwind.com.Core.Results.SuccessDataResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/mails")
public class MailController {

    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public Result sendEmail(@RequestBody MailRequest mailRequest)
    {
        return this.mailService.sendMail(mailRequest);
    }
    @PostMapping("/sendmime")
    public Result sendMimeEmail(@RequestBody MailRequest mailRequest) throws MessagingException, IOException {
        return this.mailService.sendMimeMail(mailRequest);
    }

}
